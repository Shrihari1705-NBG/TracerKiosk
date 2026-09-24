package com.tracer.kiosk.presentation.tracerbot.search

import com.tracer.kiosk.presentation.feature.navigation.model.Destination

/**
 * Searches the known campus destinations using
 * the user's natural-language query.
 *
 * This search is completely local.
 */
class DestinationSearchEngine {

    /**
     * Searches the supplied destination list.
     *
     * Results are ordered from strongest match
     * to weakest match.
     */
    fun search(
        destinations: List<Destination>,
        query: String
    ): List<Destination> {

        val normalizedQuery = normalize(query)

        if (normalizedQuery.isBlank()) {
            return emptyList()
        }

        return destinations
            .mapNotNull { destination ->

                val score =
                    calculateScore(
                        destination = destination,
                        query = normalizedQuery
                    )

                if (score > 0) {
                    destination to score
                } else {
                    null
                }
            }
            .sortedByDescending { (_, score) ->
                score
            }
            .map { (destination, _) ->
                destination
            }
    }

    /**
     * Calculates a local relevance score.
     *
     * Higher score = stronger destination match.
     */
    private fun calculateScore(
        destination: Destination,
        query: String
    ): Int {

        val normalizedName =
            normalize(destination.name)

        val normalizedAliases =
            destination.aliases.map {
                normalize(it)
            }

        // =========================================================
        // Exact destination name
        // =========================================================

        if (normalizedQueryEquals(
                query,
                normalizedName
            )
        ) {
            return 500
        }

        // =========================================================
        // Exact alias
        // =========================================================

        if (
            normalizedAliases.any { alias ->
                normalizedQueryEquals(
                    query,
                    alias
                )
            }
        ) {
            return 450
        }

        // =========================================================
        // Full destination name contained in query
        // =========================================================

        if (
            normalizedName.isNotBlank() &&
            query.contains(normalizedName)
        ) {
            return 400
        }

        // =========================================================
        // Alias contained in query
        // =========================================================

        if (
            normalizedAliases.any { alias ->
                alias.isNotBlank() &&
                        query.contains(alias)
            }
        ) {
            return 350
        }

        // =========================================================
        // Matching destination-name words
        // =========================================================

        val queryWords =
            query
                .split(" ")
                .filter {
                    it.length >= 2 &&
                            it !in STOP_WORDS
                }

        val destinationWords =
            normalizedName
                .split(" ")
                .filter {
                    it.length >= 2
                }

        val matchingWords =
            destinationWords.count { word ->
                queryWords.contains(word)
            }

        if (matchingWords >= 2) {
            return 250
        }

        if (matchingWords == 1) {
            return 150
        }

        return 0
    }

    /**
     * Compares two already-normalized strings.
     */
    private fun normalizedQueryEquals(
        first: String,
        second: String
    ): Boolean {
        return first == second
    }

    /**
     * Normalizes text before comparison.
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

            "go",
            "take",
            "guide",
            "lead",
            "navigate",
            "navigation",
            "route",
            "directions",
            "way"
        )
    }
}