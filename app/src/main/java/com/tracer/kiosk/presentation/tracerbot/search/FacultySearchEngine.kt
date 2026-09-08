package com.tracer.kiosk.presentation.tracerbot.search

import com.tracer.kiosk.presentation.tracerbot.model.Faculty

/**
 * Searches the local faculty database using
 * natural-language queries.
 *
 * The search engine is completely local.
 *
 * It supports:
 *
 * 1. Faculty name matching
 * 2. Department matching
 * 3. Course matching
 * 4. Research-interest matching
 * 5. Qualification matching
 * 6. Designation matching
 * 7. Recognition matching
 * 8. General profile matching
 *
 * Results are ranked from strongest to weakest match.
 */
class FacultySearchEngine {

    /**
     * Search the supplied faculty list.
     *
     * Example:
     *
     * "Tell me about Dr. Plasin Francis Dias"
     *
     * or:
     *
     * "Who teaches VLSI?"
     */
    fun search(
        facultyList: List<Faculty>,
        query: String
    ): List<Faculty> {

        val normalizedQuery = normalize(query)

        if (normalizedQuery.isBlank()) {
            return emptyList()
        }

        /*
         * Remove common conversational words.
         *
         * These words help form a sentence but usually
         * do not identify a faculty member.
         */
        val queryWords = normalizedQuery
            .split(" ")
            .filter { word ->
                word.isNotBlank() &&
                        word.length >= 2 &&
                        word !in STOP_WORDS
            }

        return facultyList
            .map { faculty ->

                val score = calculateScore(
                    faculty = faculty,
                    query = normalizedQuery,
                    queryWords = queryWords
                )

                faculty to score
            }
            .filter { (_, score) ->
                score > 0
            }
            .sortedByDescending { (_, score) ->
                score
            }
            .map { (faculty, _) ->
                faculty
            }
    }

    /**
     * Calculates the relevance score for one faculty member.
     *
     * Higher score = stronger match.
     */
    private fun calculateScore(
        faculty: Faculty,
        query: String,
        queryWords: List<String>
    ): Int {

        var score = 0

        // ---------------------------------------------------------
        // Normalize faculty information
        // ---------------------------------------------------------

        val name = normalize(faculty.name)
        val designation = normalize(faculty.designation)
        val qualification = normalize(faculty.qualification)
        val department = normalize(faculty.department)
        val about = normalize(faculty.about)
        val experience = normalize(faculty.experience)
        val email = normalize(faculty.email)

        val researchInterests = faculty.researchInterests
            .map { normalize(it) }

        val coursesTaught = faculty.coursesTaught
            .map { normalize(it) }

        val recognition = faculty.selectedRecognition
            .map { normalize(it) }

        // =========================================================
        // EXACT FULL NAME MATCH
        // =========================================================

        if (name == query) {
            score += 500
        }

        // =========================================================
        // FULL NAME CONTAINED IN QUERY
        // =========================================================
        //
        // Example:
        //
        // "tell me about dr plasin francis dias"
        //
        // contains:
        //
        // "plasin francis dias"
        //
        // This is a very strong match.

        if (
            name.isNotBlank() &&
            query.contains(name)
        ) {
            score += 400
        }

        // =========================================================
        // QUERY CONTAINS IMPORTANT NAME WORDS
        // =========================================================

        val nameWords = name
            .split(" ")
            .filter { word ->
                word.length >= 3 &&
                        word !in NAME_STOP_WORDS
            }

        for (nameWord in nameWords) {

            if (queryWords.contains(nameWord)) {
                score += 100
            }
        }

        // =========================================================
        // DEPARTMENT PHRASE MATCH
        // =========================================================

        if (
            department.isNotBlank() &&
            query.contains(department)
        ) {
            score += 100
        }

        // =========================================================
        // RESEARCH PHRASE MATCH
        // =========================================================

        if (
            researchInterests.any { interest ->
                interest.isNotBlank() &&
                        query.contains(interest)
            }
        ) {
            score += 120
        }

        // =========================================================
        // COURSE PHRASE MATCH
        // =========================================================

        if (
            coursesTaught.any { course ->
                course.isNotBlank() &&
                        query.contains(course)
            }
        ) {
            score += 150
        }

        // =========================================================
        // INDIVIDUAL WORD MATCHES
        // =========================================================

        for (word in queryWords) {

            // -----------------------------------------------------
            // Name
            // -----------------------------------------------------

            if (nameWords.contains(word)) {
                score += 80
                continue
            }

            // -----------------------------------------------------
            // Department
            // -----------------------------------------------------

            if (department.contains(word)) {
                score += 20
            }

            // -----------------------------------------------------
            // Research
            // -----------------------------------------------------

            if (
                researchInterests.any { interest ->
                    interest.contains(word)
                }
            ) {
                score += 30
            }

            // -----------------------------------------------------
            // Courses
            // -----------------------------------------------------

            if (
                coursesTaught.any { course ->
                    course.contains(word)
                }
            ) {
                score += 35
            }

            // -----------------------------------------------------
            // Designation
            // -----------------------------------------------------

            if (designation.contains(word)) {
                score += 12
            }

            // -----------------------------------------------------
            // Qualification
            // -----------------------------------------------------

            if (qualification.contains(word)) {
                score += 12
            }

            // -----------------------------------------------------
            // About
            // -----------------------------------------------------

            if (about.contains(word)) {
                score += 5
            }

            // -----------------------------------------------------
            // Experience
            // -----------------------------------------------------

            if (experience.contains(word)) {
                score += 5
            }

            // -----------------------------------------------------
            // Recognition
            // -----------------------------------------------------

            if (
                recognition.any { item ->
                    item.contains(word)
                }
            ) {
                score += 10
            }

            // -----------------------------------------------------
            // Email
            // -----------------------------------------------------

            if (email.contains(word)) {
                score += 10
            }
        }

        return score
    }

    /**
     * Normalize text before searching.
     *
     * Example:
     *
     * " Dr. PLASIN Francis Dias "
     *
     * becomes:
     *
     * "dr plasin francis dias"
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    companion object {

        /**
         * Common words that should not influence
         * faculty matching.
         */
        private val STOP_WORDS = setOf(

            "a",
            "an",
            "the",

            "about",
            "tell",
            "me",
            "more",

            "who",
            "what",
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

            "i",
            "you",
            "he",
            "she",
            "they",
            "his",
            "her",
            "their",

            "please",

            "give",
            "show",
            "find",

            "tell",
            "know",

            "for",
            "to",
            "of",
            "in",
            "on",
            "at",
            "with",
            "from",

            "and",
            "or"
        )

        /**
         * Words commonly appearing in faculty names
         * that should not receive name-specific weight.
         */
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