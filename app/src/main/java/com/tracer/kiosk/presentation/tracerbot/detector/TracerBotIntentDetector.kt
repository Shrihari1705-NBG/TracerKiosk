
package com.tracer.kiosk.presentation.tracerbot.detector

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent

/**
 * Local, rule-based natural-language intent detector.
 *
 * Specific information requests are checked before general
 * conversational phrases to avoid false classifications.
 */
class TracerBotIntentDetector {

    fun detect(query: String): TracerBotIntent {

        val text = normalize(query)

        if (text.isBlank()) {
            return TracerBotIntent.Unknown
        }

        // 1. Help
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

        // 2. Contact / email
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

        // 3. Publications
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

        // 4. Recognition / awards
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

        // 5. Experience
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

        // 6. Qualification / education
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

        // 7. Designation
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

        // 8. Courses / teaching
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

        // 9. Project information
        // Keep this BEFORE Navigation and Research to preserve
        // the previously tested project-information behaviour.
        if (
            containsAny(
                text,
                "tracer project",
                "what is tracer",
                "what is the tracer project",
                "project purpose",
                "project objective",
                "project goal",
                "what is the purpose of tracer",
                "what is the objective of tracer",
                "what is the goal of tracer",
                "project name",
                "what is the project name",
                "name of the project",
                "what is tracer called",
                "tracer name",
                "developer",
                "developer name",
                "project developer",
                "who developed this app",
                "who developed the app",
                "who developed tracer",
                "who made tracer",
                "who created tracer",
                "who built tracer",
                "guide",
                "project guide",
                "guide name",
                "who is the guide",
                "who is project guide",
                "who guided the project",
                "team",
                "development team",
                "project team",
                "team members",
                "developers",
                "who are the developers",
                "who are the team members",
                "tracer kiosk",
                "what is the kiosk",
                "what is tracer kiosk",
                "how does the kiosk work",
                "what can the kiosk do",
                "kiosk features",
                "kiosk functionality",
                "tracer main app",
                "what is the main app",
                "what is tracer main app",
                "how does the main app work",
                "main app features",
                "main app technologies",
                "tracerbot",
                "tracer bot",
                "what is tracerbot",
                "what is tracer bot",
                "what does tracerbot do",
                "what does tracer bot do",
                "what can tracerbot do",
                "what can tracer bot do",
                "about tracerbot",
                "about tracer bot",
                "tracerbot capabilities",
                "tracerbot features",
                "tracerbot functions",
                "tracerbot functionality",
                "technologies used",
                "what technologies are used",
                "technology stack",
                "what is the technology stack",
                "tech stack",
                "what tech stack is used",
                "what technologies does tracer use",
                "what technology does tracer use",
                "architecture",
                "project architecture",
                "system architecture",
                "what architecture is used",
                "navigation algorithm",
                "what algorithm does navigation use",
                "what algorithm is used for navigation",
                "what algorithm does tracer use for navigation",
                "pathfinding algorithm",
                "what pathfinding algorithm is used",
                "route planning algorithm",
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
                "navigation graph",
                "what is the navigation graph",
                "weighted graph",
                "what is a weighted graph",
                "navigation nodes",
                "navigation edges",
                "ble localization",
                "rssi localization",
                "bluetooth localization",
                "indoor localization",
                "how does ble localization work",
                "how does rssi localization work",
                "onnx",
                "onnx localization",
                "onnx model",
                "how is onnx used",
                "how does onnx localization work",
                "qr handoff",
                "qr code",
                "kiosk qr",
                "qr integration",
                "how does qr handoff work",
                "how does the qr handoff work",
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

        // 10. Navigation
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

        // 11. Research
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

        // 12. General faculty profile
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

        // 13. Greeting
        // Checked late so a greeting won't override a real question.
        if (isStandaloneGreeting(text)) {
            return TracerBotIntent.Greeting
        }

        // 14. Unknown
        return TracerBotIntent.Unknown
    }

    private fun isStandaloneGreeting(text: String): Boolean {

        val greetingPhrases = setOf(
            "hi",
            "hi there",
            "hello",
            "hello there",
            "hey",
            "hey there",
            "good morning",
            "good afternoon",
            "good evening",
            "good night",
            "greetings",
            "howdy",
            "how are you",
            "how are you doing",
            "what's up",
            "whats up",
            "nice to meet you"
        )

        if (text in greetingPhrases) {
            return true
        }

        // Accept short conversational greetings such as:
        // "Hey, how are you?" or "Hello, how are you doing?"
        val greetingPrefixes = listOf(
            "hi ",
            "hello ",
            "hey "
        )

        val isGreetingPrefix = greetingPrefixes.any { prefix ->
            text.startsWith(prefix)
        }

        val containsOnlyGreetingFollowUp = containsAny(
            text,
            "how are you",
            "how are you doing",
            "what's up",
            "whats up"
        )

        return isGreetingPrefix && containsOnlyGreetingFollowUp
    }

    /**
     * Preserve '*' because A* is a supported project topic.
     */
    private fun normalize(text: String): String =
        text.lowercase()
            .replace(Regex("[^a-z0-9@.+*'\\-]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()

    private fun containsAny(
        text: String,
        vararg phrases: String
    ): Boolean =
        phrases.any { phrase -> text.contains(phrase) }
}
