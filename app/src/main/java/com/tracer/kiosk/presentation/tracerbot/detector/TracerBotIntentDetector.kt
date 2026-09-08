package com.tracer.kiosk.presentation.tracerbot.detector

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent

/**
 * Detects what the user wants TracerBot to do.
 *
 * This is a lightweight, completely local,
 * rule-based natural-language intent detector.
 *
 * The detector deliberately checks more specific
 * intents before more general intents to reduce
 * false classifications.
 */
class TracerBotIntentDetector {

    /**
     * Determines the user's intent from their query.
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
        // Check this BEFORE navigation because queries such as:
        //
        // "Where can I find Dr. X's email?"
        //
        // are still contact questions.

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
        // 9. RESEARCH
        // =========================================================

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
        // 10. NAVIGATION
        // =========================================================
        //
        // Navigation is checked after information-specific
        // intents so "where is the email?" does not become
        // a navigation request.

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
                "where is"
            )
        ) {
            return TracerBotIntent.Navigate
        }

        // =========================================================
        // 11. GENERAL FACULTY PROFILE
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
        // 12. UNKNOWN
        // =========================================================

        return TracerBotIntent.Unknown
    }

    /**
     * Normalize text before intent matching.
     *
     * Example:
     *
     * "  WHAT is Dr. XYZ's EMAIL? "
     *
     * becomes approximately:
     *
     * "what is dr xyz s email"
     */
    private fun normalize(text: String): String {

        return text
            .lowercase()
            .replace(Regex("[^a-z0-9@.+-]"), " ")
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