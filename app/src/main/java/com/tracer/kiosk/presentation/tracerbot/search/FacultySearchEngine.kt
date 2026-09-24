package com.tracer.kiosk.presentation.tracerbot.search

import com.tracer.kiosk.presentation.tracerbot.model.Faculty

class FacultySearchEngine {

    /**
     * Search faculty using a natural-language query.
     *
     * This is intentionally lightweight and completely local.
     */
    fun search(
        facultyList: List<Faculty>,
        query: String
    ): List<Faculty> {

        val normalizedQuery = normalize(query)

        if (normalizedQuery.isBlank()) {
            return emptyList()
        }

        // Remove common conversational words.
        val queryWords = normalizedQuery
            .split(" ")
            .filter { it.isNotBlank() }
            .filterNot { isStopWord(it) }

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
     * Calculate how relevant a faculty member is
     * to the user's query.
     */
    private fun calculateScore(
        faculty: Faculty,
        query: String,
        queryWords: List<String>
    ): Int {

        var score = 0

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

        // ---------------------------------------------------------
        // Exact / phrase matches
        // ---------------------------------------------------------

        if (name == query) {
            score += 100
        }

        if (name.contains(query)) {
            score += 80
        }

        // ---------------------------------------------------------
        // Individual word matches
        // ---------------------------------------------------------

        for (word in queryWords) {

            if (word.length < 2) {
                continue
            }

            // Name is the strongest field.
            if (name.contains(word)) {
                score += 25
            }

            if (designation.contains(word)) {
                score += 8
            }

            if (qualification.contains(word)) {
                score += 8
            }

            if (department.contains(word)) {
                score += 10
            }

            if (about.contains(word)) {
                score += 5
            }

            if (experience.contains(word)) {
                score += 5
            }

            if (email.contains(word)) {
                score += 5
            }

            if (researchInterests.any { it.contains(word) }) {
                score += 15
            }

            if (coursesTaught.any { it.contains(word) }) {
                score += 15
            }

            if (recognition.any { it.contains(word) }) {
                score += 10
            }
        }

        return score
    }

    /**
     * Common conversational words that should not
     * strongly influence faculty matching.
     */
    private fun isStopWord(word: String): Boolean {

        return word in setOf(
            "a",
            "an",
            "the",
            "me",
            "my",
            "i",
            "you",
            "he",
            "she",
            "they",
            "him",
            "her",
            "tell",
            "give",
            "show",
            "please",
            "can",
            "could",
            "would",
            "about",
            "information",
            "details",
            "some",
            "more",
            "know",
            "want",
            "to",
            "of",
            "for",
            "is",
            "are",
            "was",
            "were",
            "do",
            "does",
            "did",
            "what",
            "who",
            "where",
            "when",
            "how",
            "much",
            "many",
            "his",
            "her"
        )
    }

    /**
     * Normalize text before searching.
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    }
}