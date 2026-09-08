package com.tracer.kiosk.presentation.tracerbot.intent

/**
 * Represents what the user wants TracerBot to do.
 *
 * This is deliberately kept separate from the UI and
 * the faculty database.
 */
sealed class TracerBotIntent {

    /**
     * User wants a general profile/about response.
     *
     * Example:
     * "Tell me about Dr. XYZ"
     */
    data object FacultyProfile : TracerBotIntent()

    /**
     * User wants to know what a faculty member teaches.
     *
     * Example:
     * "What does Dr. XYZ teach?"
     */
    data object FacultyCourses : TracerBotIntent()

    /**
     * User wants research information.
     *
     * Example:
     * "What is Dr. XYZ researching?"
     */
    data object FacultyResearch : TracerBotIntent()

    /**
     * User wants qualification information.
     *
     * Example:
     * "What is Dr. XYZ's qualification?"
     */
    data object FacultyQualification : TracerBotIntent()

    /**
     * User wants designation information.
     *
     * Example:
     * "What is Dr. XYZ's designation?"
     */
    data object FacultyDesignation : TracerBotIntent()

    /**
     * User wants experience information.
     *
     * Example:
     * "How much experience does Dr. XYZ have?"
     */
    data object FacultyExperience : TracerBotIntent()

    /**
     * User wants awards, recognition or achievements.
     *
     * Example:
     * "What awards has Dr. XYZ received?"
     */
    data object FacultyRecognition : TracerBotIntent()

    /**
     * User wants publication information.
     *
     * Example:
     * "How many publications does Dr. XYZ have?"
     */
    data object FacultyPublications : TracerBotIntent()

    /**
     * User wants contact information.
     *
     * Example:
     * "What is Dr. XYZ's email?"
     */
    data object FacultyContact : TracerBotIntent()

    /**
     * User wants to navigate somewhere.
     *
     * Example:
     * "Take me to Dr. XYZ's office."
     */
    data object Navigate : TracerBotIntent()

    /**
     * User is asking for help using TracerBot or the Kiosk.
     *
     * Example:
     * "What can you do?"
     */
    data object Help : TracerBotIntent()

    /**
     * User's question could not be understood.
     */
    data object Unknown : TracerBotIntent()
}