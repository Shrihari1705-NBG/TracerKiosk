package com.tracer.kiosk.presentation.tracerbot.detector

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent

/**
 * Detects what the user wants TracerBot to do.
 *
 * This is a lightweight, completely local,
 * rule-based natural-language intent detector.
 *
 * The detector checks more specific intents before
 * more general intents to reduce false classifications.
 */
class TracerBotIntentDetector {

    /**
     * Determines the user's intent from the user's query.
     */
    fun detect(query: String): TracerBotIntent {

        val text = normalize(query)

        if (text.isBlank()) {
            return TracerBotIntent.Unknown
        }

        // =========================================================
        // 1. HELP
        // =========================================================

        if (
            containsAny(
                text,
                "help",
                "what can you do",
                "what can you help with",
                "how can you help",
                "what do you do",
                "how does this work",
                "how can i use you"
            )
        ) {
            return TracerBotIntent.Help
        }

        // =========================================================
        // 2. CONTACT / EMAIL
        // =========================================================
        //
        // Checked before navigation so questions such as:
        //
        // "Where can I find Dr. X's email?"
        //
        // remain contact questions.

        if (
            containsAny(
                text,
                "email",
                "email id",
                "email address",
                "mail id",
                "mail address",
                "contact",
                "contact information",
                "contact details",
                "how can i contact"
            )
        ) {
            return TracerBotIntent.FacultyContact
        }

        // =========================================================
        // 3. PUBLICATIONS
        // =========================================================

        if (
            containsAny(
                text,
                "publication",
                "publications",
                "paper",
                "papers",
                "research paper",
                "research papers",
                "published",
                "how many papers"
            )
        ) {
            return TracerBotIntent.FacultyPublications
        }

        // =========================================================
        // 4. RECOGNITION / AWARDS
        // =========================================================

        if (
            containsAny(
                text,
                "award",
                "awards",
                "recognition",
                "achievement",
                "achievements",
                "honor",
                "honours",
                "honors",
                "award received",
                "awards received"
            )
        ) {
            return TracerBotIntent.FacultyRecognition
        }

        // =========================================================
        // 5. EXPERIENCE
        // =========================================================

        if (
            containsAny(
                text,
                "experience",
                "years of experience",
                "work experience",
                "teaching experience",
                "professional experience",
                "how experienced",
                "how many years"
            )
        ) {
            return TracerBotIntent.FacultyExperience
        }

        // =========================================================
        // 6. QUALIFICATION / EDUCATION
        // =========================================================

        if (
            containsAny(
                text,
                "qualification",
                "qualifications",
                "degree",
                "degrees",
                "education",
                "educational background",
                "academic background",
                "studied",
                "educational qualification"
            )
        ) {
            return TracerBotIntent.FacultyQualification
        }

        // =========================================================
        // 7. DESIGNATION
        // =========================================================

        if (
            containsAny(
                text,
                "designation",
                "position",
                "post",
                "role",
                "job title",
                "title"
            )
        ) {
            return TracerBotIntent.FacultyDesignation
        }

        // =========================================================
        // 8. COURSES / TEACHING
        // =========================================================

        if (
            containsAny(
                text,
                "course",
                "courses",
                "teach",
                "teaches",
                "teaching",
                "subject",
                "subjects",
                "class",
                "classes",
                "what does he teach",
                "what does she teach",
                "what do they teach",
                "what does professor teach"
            )
        ) {
            return TracerBotIntent.FacultyCourses
        }

        // =========================================================
        // 9. PROJECT INFORMATION
        // =========================================================
        //
        // IMPORTANT:
        //
        // ProjectInfo is checked BEFORE Research and Navigation.
        //
        // This prevents questions such as:
        //
        // "What algorithm does navigation use?"
        //
        // from being classified as Navigate.
        //
        // It also prevents project questions containing
        // "research" or "navigation" from being classified
        // as faculty research or campus navigation.
        //
        // A* is also handled explicitly.

        if (
            containsAny(
                text,

                // Tracer project
                "tracer project",
                "what is tracer",
                "what is the tracer project",
                "project purpose",
                "project objective",
                "project goal",
                "what is the purpose of tracer",
                "what is the objective of tracer",
                "what is the goal of tracer",

                // Tracer Kiosk
                "tracer kiosk",
                "what is the kiosk",
                "what is tracer kiosk",
                "how does the kiosk work",
                "what can the kiosk do",
                "kiosk features",
                "kiosk functionality",

                // Tracer Main App
                "tracer main app",
                "what is the main app",
                "what is tracer main app",
                "how does the main app work",
                "main app features",
                "main app technologies",

                // Technologies
                "technologies used",
                "what technologies are used",
                "technology stack",
                "what is the technology stack",
                "tech stack",
                "what tech stack is used",
                "what technologies does tracer use",
                "what technology does tracer use",

                // Architecture
                "architecture",
                "project architecture",
                "system architecture",
                "what architecture is used",

                // Navigation algorithm
                "navigation algorithm",
                "what algorithm does navigation use",
                "what algorithm is used for navigation",
                "what algorithm does tracer use for navigation",
                "pathfinding algorithm",
                "what pathfinding algorithm is used",
                "route planning algorithm",

                // A*
                "a star",
                "a*",
                "astar",
                "what is a star",
                "what is a*",
                "what is astar",
                "a star algorithm",
                "a* algorithm",
                "astar algorithm",
                "how does a star work",
                "how does a* work",
                "how does astar work",

                // Navigation graph
                "navigation graph",
                "what is the navigation graph",
                "weighted graph",
                "what is a weighted graph",
                "navigation nodes",
                "navigation edges",

                // BLE / RSSI localization
                "ble localization",
                "rssi localization",
                "bluetooth localization",
                "indoor localization",
                "how does ble localization work",
                "how does rssi localization work",

                // ONNX
                "onnx",
                "onnx localization",
                "onnx model",
                "how is onnx used",
                "how does onnx localization work",

                // QR handoff
                "qr handoff",
                "qr code",
                "kiosk qr",
                "qr integration",
                "how does qr handoff work",
                "how does the qr handoff work",

                // Kiosk ↔ Main App integration
                "kiosk main app",
                "how does kiosk and main app work",
                "how does the kiosk and main app work",
                "how does kiosk and main app work together",
                "how does the kiosk and main app work together",
                "how do kiosk and main app work",
                "how do the kiosk and main app work",
                "how do kiosk and main app work together",
                "how do the kiosk and main app work together",
                "how does the kiosk connect to the main app",
                "how does the main app connect to the kiosk",

                // Complete system workflow
                "system workflow",
                "project workflow",
                "overall workflow",
                "end to end",
                "end-to-end",
                "how does the whole system work",
                "how does the entire system work",
                "how does the tracer system work"
            )
        ) {
            return TracerBotIntent.ProjectInfo
        }

        // =========================================================
        // 10. NAVIGATION
        // =========================================================
        //
        // Navigation can refer to:
        //
        // - Faculty cabins
        // - Laboratories
        // - Classrooms
        // - Library
        // - Offices
        // - Other campus destinations
        //
        // Destination resolution happens later inside
        // TracerBotQueryProcessor.

        if (
            containsAny(
                text,
                "navigate",
                "navigation",
                "take me to",
                "guide me to",
                "show me the way",
                "how do i get to",
                "directions to",
                "route to",
                "find the way to",
                "lead me to",
                "go to",
                "where is",
                "where can i find",
                "how do i reach",
                "how can i reach",
                "take me there"
            )
        ) {
            return TracerBotIntent.Navigate
        }

        // =========================================================
        // 11. RESEARCH
        // =========================================================
        //
        // This remains after ProjectInfo.
        //
        // Therefore:
        //
        // "What is Plasin's research?"
        //
        // still becomes FacultyResearch.
        //
        // But a project question containing navigation /
        // algorithm terms is already captured above.

        if (
            containsAny(
                text,
                "research",
                "research interest",
                "research interests",
                "research area",
                "research areas",
                "research field",
                "research fields",
                "working on",
                "specialization",
                "specializes",
                "area of research"
            )
        ) {
            return TracerBotIntent.FacultyResearch
        }

        // =========================================================
        // 12. GENERAL FACULTY PROFILE
        // =========================================================

        if (
            containsAny(
                text,
                "about",
                "tell me about",
                "who is",
                "profile",
                "information about",
                "details about",
                "introduce",
                "tell me more about"
            )
        ) {
            return TracerBotIntent.FacultyProfile
        }

        // =========================================================
        // 13. UNKNOWN
        // =========================================================

        return TracerBotIntent.Unknown
    }

    /**
     * Normalize text before intent matching.
     *
     * IMPORTANT:
     *
     * The '*' character is intentionally preserved because
     * A* is one of the supported project-information topics.
     *
     * Example:
     *
     * "What is A*?"
     *
     * becomes:
     *
     * "what is a*"
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+*\\-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    /**
     * Returns true when the text contains
     * at least one supplied phrase.
     */
    private fun containsAny(
        text: String,
        vararg phrases: String
    ): Boolean {

        return phrases.any { phrase ->
            text.contains(phrase)
        }
    }
}