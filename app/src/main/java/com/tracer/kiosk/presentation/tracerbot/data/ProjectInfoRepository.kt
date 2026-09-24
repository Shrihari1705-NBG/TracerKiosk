package com.tracer.kiosk.presentation.tracerbot.data

/**
 * A single piece of verified information about the
 * Tracer project.
 *
 * The information is intended to be used by TracerBot
 * when answering project-related questions.
 */
data class ProjectInfoEntry(
    val id: String,
    val title: String,
    val answer: String,
    val keywords: List<String>
)

/**
 * Repository containing project information that
 * TracerBot can explain to users.
 *
 * Information is organized around the complete
 * Tracer ecosystem while clearly distinguishing:
 *
 * 1. Tracer Kiosk
 * 2. Tracer Main App
 * 3. Their integration
 */
object ProjectInfoRepository {

    val entries = listOf(

        // =========================================================
        // TRACER PROJECT
        // =========================================================

        ProjectInfoEntry(
            id = "project_overview",
            title = "Tracer Project",
            answer =
                "Tracer is an indoor navigation system designed " +
                        "for smart campus environments. The system " +
                        "includes the Tracer Kiosk as a public-facing " +
                        "information and destination-selection interface " +
                        "and the Tracer Main App as the user's personal " +
                        "indoor navigation application.",
            keywords = listOf(
                "tracer",
                "project",
                "tracer project",
                "what is tracer",
                "what is the tracer project",
                "system",
                "overall project",
                "whole project",
                "tracer ecosystem"
            )
        ),

        ProjectInfoEntry(
            id = "project_purpose",
            title = "Project Purpose",
            answer =
                "The Tracer project is designed to improve indoor " +
                        "campus navigation. It combines destination " +
                        "discovery, department information, indoor " +
                        "localization, and route planning to help users " +
                        "find locations inside the campus.",
            keywords = listOf(
                "purpose",
                "objective",
                "goal",
                "project purpose",
                "project objective",
                "project goal",
                "what is the purpose of tracer",
                "what is tracer for",
                "why was tracer developed",
                "why was tracer created"
            )
        ),

        // =========================================================
        // TRACER KIOSK
        // =========================================================

        ProjectInfoEntry(
            id = "kiosk_overview",
            title = "Tracer Kiosk",
            answer =
                "Tracer Kiosk is a dedicated Android tablet " +
                        "application that acts as a fixed information " +
                        "and navigation terminal at the department " +
                        "entrance. It is intended to help first-time " +
                        "visitors, students, parents, faculty, and guests " +
                        "discover department information and campus " +
                        "destinations without requiring a personal " +
                        "smartphone.",
            keywords = listOf(
                "kiosk",
                "tracer kiosk",
                "what is the kiosk",
                "what is tracer kiosk",
                "tablet",
                "information terminal",
                "information kiosk"
            )
        ),

        ProjectInfoEntry(
            id = "kiosk_features",
            title = "Tracer Kiosk Features",
            answer =
                "Tracer Kiosk provides faculty and staff information, " +
                        "department information, destination discovery, " +
                        "categorized browsing for laboratories, classrooms, " +
                        "offices and facilities, and interactive indoor " +
                        "navigation through a floor map.",
            keywords = listOf(
                "kiosk features",
                "kiosk functionality",
                "what can the kiosk do",
                "what can kiosk do",
                "kiosk functions",
                "features of tracer kiosk"
            )
        ),

        ProjectInfoEntry(
            id = "kiosk_technologies",
            title = "Tracer Kiosk Technologies",
            answer =
                "Tracer Kiosk was developed using Kotlin, Android Studio, " +
                        "Jetpack Compose, Material 3, and the MVVM architecture. " +
                        "The application uses reusable Compose components and " +
                        "is optimized for large-screen Android tablet devices.",
            keywords = listOf(
                "kiosk technology",
                "kiosk technologies",
                "kiosk tech stack",
                "technologies used in kiosk",
                "technologies used in tracer kiosk",
                "what technologies are used in the kiosk",
                "what technologies does the kiosk use",
                "kotlin kiosk",
                "jetpack compose kiosk",
                "material 3 kiosk",
                "mvvm kiosk"
            )
        ),

        // =========================================================
        // NAVIGATION
        // =========================================================

        ProjectInfoEntry(
            id = "navigation_algorithm",
            title = "Navigation Algorithm",
            answer =
                "Tracer uses the A* (A-Star) pathfinding algorithm " +
                        "for indoor route planning. The indoor map is " +
                        "modeled as a weighted graph containing navigation " +
                        "nodes and walkable edges. Each destination is mapped " +
                        "to its corresponding graph node, and A* computes the " +
                        "route between the source and destination nodes.",
            keywords = listOf(
                "navigation algorithm",
                "what algorithm does navigation use",
                "what algorithm is used for navigation",
                "what algorithm does tracer use for navigation",
                "pathfinding algorithm",
                "what pathfinding algorithm is used",
                "route planning algorithm",
                "path planning algorithm"
            )
        ),

        ProjectInfoEntry(
            id = "navigation_graph",
            title = "Navigation Graph",
            answer =
                "The Tracer navigation map is modeled as a weighted graph. " +
                        "Important corridor intersections, room entrances, " +
                        "and turning points are represented as navigation nodes. " +
                        "Walkable connections between nodes form the graph edges, " +
                        "and edge weights represent physical walking distances. " +
                        "The documented navigation graph contains 28 nodes.",
            keywords = listOf(
                "navigation graph",
                "what is the navigation graph",
                "graph",
                "nodes",
                "edges",
                "28 nodes",
                "weighted graph",
                "what is a weighted graph",
                "navigation nodes",
                "navigation edges"
            )
        ),

        ProjectInfoEntry(
            id = "a_star_explanation",
            title = "A* Pathfinding",
            answer =
                "A* calculates a route using three values. g(n) represents " +
                        "the distance from the source node, h(n) represents " +
                        "the estimated distance to the destination, and " +
                        "f(n) = g(n) + h(n). A* selects the node with the lowest " +
                        "f(n), expands its walkable neighbours, updates shorter " +
                        "paths when found, and reconstructs the route after " +
                        "reaching the destination.",
            keywords = listOf(
                "what is a star",
                "what is a*",
                "what is astar",
                "a star",
                "a*",
                "astar",
                "a star algorithm",
                "a* algorithm",
                "astar algorithm",
                "how does a star work",
                "how does a* work",
                "how does astar work"
            )
        ),

        // =========================================================
        // TRACER MAIN APP
        // =========================================================

        ProjectInfoEntry(
            id = "main_app_overview",
            title = "Tracer Main App",
            answer =
                "Tracer Main App is the personal mobile application " +
                        "in the Tracer system. It provides indoor localization " +
                        "and navigation for the user. The Main App obtains the " +
                        "user's indoor position through the BLE RSSI fingerprinting " +
                        "and ONNX localization system and uses that position as " +
                        "the starting point for navigation.",
            keywords = listOf(
                "main app",
                "tracer main app",
                "what is the main app",
                "what is tracer main app",
                "mobile app",
                "smartphone app",
                "personal navigation",
                "personal mobile application"
            )
        ),

        ProjectInfoEntry(
            id = "main_app_technologies",
            title = "Tracer Main App Technologies",
            answer =
                "The Tracer Android application uses Kotlin, Android Studio, " +
                        "Jetpack Compose, Material Design 3, MVVM architecture, " +
                        "Hilt dependency injection, Kotlin Coroutines, StateFlow, " +
                        "DataStore Preferences, the Android Bluetooth API, and " +
                        "Android Location Services API.",
            keywords = listOf(
                "main app technologies",
                "main app tech stack",
                "technologies used in main app",
                "technologies used in tracer main app",
                "what technologies are used in the main app",
                "what technologies does the main app use",
                "android technologies",
                "mobile app technologies",
                "tracer app technology"
            )
        ),

        // =========================================================
        // LOCALIZATION
        // =========================================================

        ProjectInfoEntry(
            id = "ble_localization",
            title = "BLE Localization",
            answer =
                "The Tracer Main App uses BLE RSSI fingerprinting for indoor " +
                        "localization. RSSI values detected from the configured " +
                        "BLE beacons are formed into an RSSI input vector. The " +
                        "localization model predicts the user's current indoor " +
                        "node from that vector.",
            keywords = listOf(
                "ble",
                "ble localization",
                "rssi",
                "rssi localization",
                "bluetooth localization",
                "indoor localization",
                "how does ble localization work",
                "how does rssi localization work"
            )
        ),

        ProjectInfoEntry(
            id = "onnx_localization",
            title = "ONNX Localization",
            answer =
                "The trained indoor localization model is integrated into the " +
                        "Tracer Android application using ONNX-based inference. " +
                        "The RSSI vector is passed to the model, which predicts " +
                        "the current indoor node. The predicted node is then " +
                        "provided to the localization and navigation components.",
            keywords = listOf(
                "onnx",
                "onnx model",
                "onnx localization",
                "how is onnx used",
                "how does onnx localization work",
                "localization model",
                "machine learning localization"
            )
        ),

        // =========================================================
        // QR HANDOFF
        // =========================================================

        ProjectInfoEntry(
            id = "qr_handoff",
            title = "QR Handoff",
            answer =
                "The QR Handoff connects the Tracer Kiosk and Tracer Main App. " +
                        "The user selects a destination at the Kiosk, and the " +
                        "Kiosk generates a QR code containing the destination " +
                        "information. The user scans the QR code using the Main App. " +
                        "The Main App identifies the destination and passes it to " +
                        "the navigation system.",
            keywords = listOf(
                "qr",
                "qr handoff",
                "qr code",
                "kiosk qr",
                "qr integration",
                "handoff",
                "how does qr handoff work",
                "how does the qr handoff work"
            )
        ),

        // =========================================================
        // KIOSK + MAIN APP WORKFLOW
        // =========================================================

        ProjectInfoEntry(
            id = "kiosk_main_app_workflow",
            title = "Kiosk and Main App Workflow",
            answer =
                "The overall workflow is: the user selects a destination " +
                        "at the Tracer Kiosk, the Kiosk generates a QR code, " +
                        "the user scans it with the Tracer Main App, the destination " +
                        "is identified, the Main App obtains the user's current " +
                        "BLE-localized node, and A* calculates the route from the " +
                        "current node to the selected destination.",
            keywords = listOf(
                "workflow",
                "kiosk main app",
                "how does kiosk and app work",
                "how does the kiosk and main app work",
                "how does the kiosk and main app work together",
                "how do the kiosk and main app work",
                "how do the kiosk and main app work together",
                "how does the kiosk connect to the main app",
                "how does the main app connect to the kiosk",
                "system workflow",
                "project workflow",
                "overall workflow",
                "end to end",
                "end-to-end",
                "how does the whole system work",
                "how does the entire system work"
            )
        )
    )

    /**
     * Finds the most relevant project-information entry
     * for a user's query.
     *
     * Longer and more specific keyword phrases receive
     * higher scores than generic keywords.
     */
    fun findBestMatch(query: String): ProjectInfoEntry? {

        val normalizedQuery = normalize(query)

        if (normalizedQuery.isBlank()) {
            return null
        }

        return entries
            .map { entry ->

                val bestScore = entry.keywords
                    .map { keyword ->
                        scoreKeyword(
                            query = normalizedQuery,
                            keyword = normalize(keyword)
                        )
                    }
                    .maxOrNull() ?: 0

                entry to bestScore
            }
            .filter { (_, score) ->
                score > 0
            }
            .maxByOrNull { (_, score) ->
                score
            }
            ?.first
    }

    /**
     * Scores one keyword against the normalized query.
     *
     * More specific phrases receive higher scores.
     */
    private fun scoreKeyword(
        query: String,
        keyword: String
    ): Int {

        if (query.isBlank() || keyword.isBlank()) {
            return 0
        }

        // Exact match is strongest.
        if (query == keyword) {
            return 1000 + keyword.length
        }

        // Exact phrase appears in the user's question.
        if (query.contains(keyword)) {
            return 500 + (keyword.length * 5)
        }

        // User query appears inside the keyword.
        if (keyword.contains(query)) {
            return 300 + (query.length * 3)
        }

        // Fall back to word overlap.
        val queryWords = query
            .split(" ")
            .filter { it.isNotBlank() }

        val keywordWords = keyword
            .split(" ")
            .filter { it.isNotBlank() }

        val commonWords = keywordWords.count { word ->
            word.length >= 3 && queryWords.contains(word)
        }

        return if (commonWords > 0) {
            50 + (commonWords * 20) + keywordWords.size
        } else {
            0
        }
    }

    /**
     * Normalizes text while preserving '*' because
     * A* is a supported project topic.
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+*\\-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    }
}