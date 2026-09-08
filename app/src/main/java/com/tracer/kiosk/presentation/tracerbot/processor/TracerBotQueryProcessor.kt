package com.tracer.kiosk.presentation.tracerbot.processor

import com.tracer.kiosk.presentation.tracerbot.data.FacultyRepository
import com.tracer.kiosk.presentation.tracerbot.detector.TracerBotIntentDetector
import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import com.tracer.kiosk.presentation.tracerbot.query.TracerBotQuery
import com.tracer.kiosk.presentation.tracerbot.search.FacultySearchEngine

/**
 * Processes a complete TracerBot user query.
 *
 * Responsibilities:
 *
 * 1. Detect the user's intent.
 * 2. Load faculty data from the local repository.
 * 3. Search for relevant faculty members.
 * 4. Determine whether the strongest match is reliable.
 * 5. Calculate a local relevance score.
 * 6. Create a structured TracerBotQuery object.
 *
 * This class contains no UI code.
 *
 * All processing is performed locally.
 */
class TracerBotQueryProcessor(
    private val facultyRepository: FacultyRepository,
    private val intentDetector: TracerBotIntentDetector =
        TracerBotIntentDetector(),
    private val facultySearchEngine: FacultySearchEngine =
        FacultySearchEngine()
) {

    /**
     * Minimum search score required before a faculty member
     * is considered a reliable primary match.
     *
     * This is a local search threshold.
     *
     * It is NOT an AI confidence probability.
     */
    private val minimumFacultyScore = 80

    /**
     * Processes the user's natural-language question.
     */
    fun process(query: String): TracerBotQuery {

        val cleanQuery = query.trim()

        // =========================================================
        // Empty query
        // =========================================================

        if (cleanQuery.isBlank()) {

            return TracerBotQuery(
                originalText = query,
                intent = TracerBotIntent.Unknown
            )
        }

        // =========================================================
        // Detect intent
        // =========================================================

        val intent = intentDetector.detect(cleanQuery)

        // =========================================================
        // Load faculty data
        // =========================================================

        val facultyList =
            facultyRepository.getAllFaculty()

        // =========================================================
        // Search faculty
        // =========================================================

        val facultyMatches =
            facultySearchEngine.search(
                facultyList = facultyList,
                query = cleanQuery
            )

        // =========================================================
        // Determine strongest faculty match
        // =========================================================

        /*
         * FacultySearchEngine returns results ordered
         * from strongest to weakest.
         *
         * We therefore inspect the first result.
         */
        val searchScore =
            calculateSearchScore(
                faculty = facultyMatches.firstOrNull(),
                query = cleanQuery
            )

        val bestFaculty =
            if (
                facultyMatches.isNotEmpty() &&
                searchScore >= minimumFacultyScore
            ) {
                facultyMatches.first()
            } else {
                null
            }

        // =========================================================
        // Calculate confidence
        // =========================================================

        val confidenceScore =
            calculateConfidence(
                facultyMatches = facultyMatches,
                searchScore = searchScore
            )

        // =========================================================
        // Navigation
        // =========================================================

        val requiresNavigation =
            intent == TracerBotIntent.Navigate

        // =========================================================
        // Build structured query
        // =========================================================

        return TracerBotQuery(

            originalText = cleanQuery,

            intent = intent,

            faculty = bestFaculty,

            facultyMatches = facultyMatches,

            confidenceScore = confidenceScore,

            requiresNavigation = requiresNavigation
        )
    }

    /**
     * Calculates an approximate local score for the strongest
     * faculty result.
     *
     * The search engine currently exposes only the ordered
     * faculty results, not the internal score.
     *
     * Therefore this method uses the available faculty data
     * and the original query to estimate how strong the match is.
     */
    private fun calculateSearchScore(
        faculty: Faculty?,
        query: String
    ): Int {

        if (faculty == null) {
            return 0
        }

        val normalizedQuery =
            normalize(query)

        val normalizedName =
            normalize(faculty.name)

        // ---------------------------------------------------------
        // Exact name
        // ---------------------------------------------------------

        if (normalizedName == normalizedQuery) {
            return 500
        }

        // ---------------------------------------------------------
        // Full faculty name contained in query
        // ---------------------------------------------------------

        if (
            normalizedName.isNotBlank() &&
            normalizedQuery.contains(normalizedName)
        ) {
            return 400
        }

        // ---------------------------------------------------------
        // Count matching name words
        // ---------------------------------------------------------

        val queryWords =
            normalizedQuery
                .split(" ")
                .filter {
                    it.length >= 3 &&
                            it !in STOP_WORDS
                }

        val nameWords =
            normalizedName
                .split(" ")
                .filter {
                    it.length >= 3 &&
                            it !in NAME_STOP_WORDS
                }

        val matchingNameWords =
            nameWords.count { nameWord ->
                queryWords.contains(nameWord)
            }

        if (matchingNameWords >= 2) {
            return 250
        }

        if (matchingNameWords == 1) {
            return 100
        }

        // ---------------------------------------------------------
        // Course match
        // ---------------------------------------------------------

        val normalizedCourses =
            faculty.coursesTaught.map {
                normalize(it)
            }

        if (
            normalizedCourses.any {
                normalizedQuery.contains(it) ||
                        it.contains(normalizedQuery)
            }
        ) {
            return 150
        }

        // ---------------------------------------------------------
        // Research match
        // ---------------------------------------------------------

        val normalizedResearch =
            faculty.researchInterests.map {
                normalize(it)
            }

        if (
            normalizedResearch.any {
                normalizedQuery.contains(it) ||
                        it.contains(normalizedQuery)
            }
        ) {
            return 120
        }

        // ---------------------------------------------------------
        // Department match
        // ---------------------------------------------------------

        val normalizedDepartment =
            normalize(faculty.department)

        if (
            normalizedDepartment.isNotBlank() &&
            normalizedQuery.contains(normalizedDepartment)
        ) {
            return 100
        }

        // ---------------------------------------------------------
        // General weak match
        // ---------------------------------------------------------

        return 0
    }

    /**
     * Converts the search result into a simple local
     * confidence value.
     *
     * Again, this is NOT an AI probability.
     */
    private fun calculateConfidence(
        facultyMatches: List<Faculty>,
        searchScore: Int
    ): Int {

        if (facultyMatches.isEmpty()) {
            return 0
        }

        return when {

            // Very strong match
            searchScore >= 400 -> 100

            // Strong name match
            searchScore >= 250 -> 95

            // Good individual-name match
            searchScore >= 100 -> 85

            // Course/research/department match
            searchScore >= 80 -> 75

            // Several possible matches
            facultyMatches.size <= 3 -> 50

            // Large number of weak matches
            else -> 25
        }
    }

    /**
     * Normalize text before comparison.
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    companion object {

        private val STOP_WORDS = setOf(

            "about",
            "tell",
            "what",
            "who",
            "which",
            "where",
            "when",
            "why",
            "how",

            "does",
            "do",
            "did",
            "is",
            "are",
            "was",
            "were",

            "can",
            "could",
            "would",
            "should",

            "please",
            "give",
            "show",
            "find",
            "me",

            "the",
            "a",
            "an",
            "to",
            "of",
            "in",
            "on",
            "at",
            "for",
            "with",

            "and",
            "or",

            "prof",
            "professor",
            "dr",
            "mr",
            "mrs",
            "ms"
        )

        private val NAME_STOP_WORDS = setOf(
            "dr",
            "prof",
            "professor",
            "mr",
            "mrs",
            "ms",
            "miss"
        )
    }
}