package com.tracer.kiosk.presentation.tracerbot.response

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import com.tracer.kiosk.presentation.tracerbot.query.TracerBotQuery

/**
 * Generates the final human-readable response for TracerBot.
 *
 * This class contains no UI code.
 *
 * It converts:
 *
 * TracerBotQuery
 *      ↓
 * Human-readable response
 *
 * All responses are generated locally from the
 * faculty information stored in the application.
 */
class TracerBotResponseEngine {

    /**
     * Generate a response for the processed query.
     */
    fun generateResponse(
        query: TracerBotQuery
    ): TracerBotResponse {

        // =========================================================
        // Handle special intents first
        // =========================================================

        when (query.intent) {

            TracerBotIntent.Help -> {
                return createHelpResponse()
            }

            TracerBotIntent.Unknown -> {
                return createUnknownResponse()
            }

            else -> {
                // Faculty-related intents continue below.
            }
        }

        // =========================================================
        // No faculty found
        // =========================================================

        if (query.faculty == null) {

            // The query may still have multiple useful matches.
            if (query.facultyMatches.isNotEmpty()) {

                return createMultipleFacultyResponse(
                    query = query
                )
            }

            return createNoFacultyResponse(
                query = query
            )
        }

        // =========================================================
        // Generate faculty-specific response
        // =========================================================

        val faculty = query.faculty

        return when (query.intent) {

            TracerBotIntent.FacultyProfile ->
                createProfileResponse(faculty)

            TracerBotIntent.FacultyCourses ->
                createCoursesResponse(faculty)

            TracerBotIntent.FacultyResearch ->
                createResearchResponse(faculty)

            TracerBotIntent.FacultyQualification ->
                createQualificationResponse(faculty)

            TracerBotIntent.FacultyDesignation ->
                createDesignationResponse(faculty)

            TracerBotIntent.FacultyExperience ->
                createExperienceResponse(faculty)

            TracerBotIntent.FacultyRecognition ->
                createRecognitionResponse(faculty)

            TracerBotIntent.FacultyPublications ->
                createPublicationResponse(faculty)

            TracerBotIntent.FacultyContact ->
                createContactResponse(faculty)

            TracerBotIntent.Navigate ->
                createNavigationResponse(faculty)

            else ->
                createProfileResponse(faculty)
        }
    }

    // =================================================================
    // FACULTY PROFILE
    // =================================================================

    private fun createProfileResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val message = buildString {

            append("Here's some information about ")
            append(faculty.name)
            append(".\n\n")

            if (faculty.designation.isNotBlank()) {
                append("Designation: ")
                append(faculty.designation)
                append("\n")
            }

            if (faculty.department.isNotBlank()) {
                append("Department: ")
                append(faculty.department)
                append("\n")
            }

            if (faculty.qualification.isNotBlank()) {
                append("Qualification: ")
                append(faculty.qualification)
                append("\n")
            }

            if (faculty.experience.isNotBlank()) {
                append("Experience: ")
                append(faculty.experience)
                append("\n")
            }

            if (faculty.about.isNotBlank()) {

                append("\nAbout:\n")
                append(faculty.about)
            }
        }

        return facultyResponse(
            message = message.toString(),
            faculty = faculty
        )
    }

    // =================================================================
    // COURSES
    // =================================================================

    private fun createCoursesResponse(
        faculty: Faculty
    ): TracerBotResponse {

        if (faculty.coursesTaught.isEmpty()) {

            return facultyResponse(
                message =
                    "I don't have course information available for " +
                            faculty.name + ".",
                faculty = faculty
            )
        }

        val message = buildString {

            append(faculty.name)
            append(" teaches:\n\n")

            faculty.coursesTaught.forEach { course ->
                append("• ")
                append(course)
                append("\n")
            }
        }

        return facultyResponse(
            message = message.toString().trim(),
            faculty = faculty
        )
    }

    // =================================================================
    // RESEARCH
    // =================================================================

    private fun createResearchResponse(
        faculty: Faculty
    ): TracerBotResponse {

        if (faculty.researchInterests.isEmpty()) {

            return facultyResponse(
                message =
                    "I don't have research-interest information " +
                            "available for ${faculty.name}.",
                faculty = faculty
            )
        }

        val message = buildString {

            append(faculty.name)
            append("'s research interests include:\n\n")

            faculty.researchInterests.forEach { interest ->
                append("• ")
                append(interest)
                append("\n")
            }
        }

        return facultyResponse(
            message = message.toString().trim(),
            faculty = faculty
        )
    }

    // =================================================================
    // QUALIFICATION
    // =================================================================

    private fun createQualificationResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val qualification = faculty.qualification.trim()

        val message =
            if (qualification.isNotBlank()) {

                "${faculty.name}'s qualification is " +
                        "$qualification."

            } else {

                "I don't have qualification information " +
                        "available for ${faculty.name}."
            }

        return facultyResponse(
            message = message,
            faculty = faculty
        )
    }

    // =================================================================
    // DESIGNATION
    // =================================================================

    private fun createDesignationResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val designation = faculty.designation.trim()

        val message =
            if (designation.isNotBlank()) {

                "${faculty.name} is a $designation."

            } else {

                "I don't have designation information " +
                        "available for ${faculty.name}."
            }

        return facultyResponse(
            message = message,
            faculty = faculty
        )
    }

    // =================================================================
    // EXPERIENCE
    // =================================================================

    private fun createExperienceResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val experience = faculty.experience.trim()

        val message =
            if (experience.isNotBlank()) {

                "${faculty.name} has $experience."

            } else {

                "I don't have experience information " +
                        "available for ${faculty.name}."
            }

        return facultyResponse(
            message = message,
            faculty = faculty
        )
    }

    // =================================================================
    // RECOGNITION
    // =================================================================

    private fun createRecognitionResponse(
        faculty: Faculty
    ): TracerBotResponse {

        if (faculty.selectedRecognition.isEmpty()) {

            return facultyResponse(
                message =
                    "I don't have recognition or achievement " +
                            "information available for ${faculty.name}.",
                faculty = faculty
            )
        }

        val message = buildString {

            append("Selected recognition and achievements of ")
            append(faculty.name)
            append(":\n\n")

            faculty.selectedRecognition.forEach { recognition ->

                append("• ")
                append(recognition)
                append("\n")
            }
        }

        return facultyResponse(
            message = message.toString().trim(),
            faculty = faculty
        )
    }

    // =================================================================
    // PUBLICATIONS
    // =================================================================

    private fun createPublicationResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val publications = faculty.publications

        val message =
            if (publications != null) {

                "${faculty.name} has $publications " +
                        "listed publications."

            } else {

                "I don't have publication information " +
                        "available for ${faculty.name}."
            }

        return facultyResponse(
            message = message,
            faculty = faculty
        )
    }

    // =================================================================
    // CONTACT
    // =================================================================

    private fun createContactResponse(
        faculty: Faculty
    ): TracerBotResponse {

        val email = faculty.email.trim()

        val message =
            if (email.isNotBlank()) {

                "You can contact ${faculty.name} at:\n\n$email"

            } else {

                "I don't have contact information " +
                        "available for ${faculty.name}."
            }

        return facultyResponse(
            message = message,
            faculty = faculty
        )
    }

    // =================================================================
    // NAVIGATION
    // =================================================================

    private fun createNavigationResponse(
        faculty: Faculty
    ): TracerBotResponse {

        return TracerBotResponse(

            message =
                "I can help you find ${faculty.name}. " +
                        "Let's navigate to their location.",

            facultyName = faculty.name,

            facultyMatches = listOf(faculty),

            showNavigationAction = true,

            navigationDestination = faculty.name,

            hasFacultyInformation = true
        )
    }

    // =================================================================
    // HELP
    // =================================================================

    private fun createHelpResponse(): TracerBotResponse {

        return TracerBotResponse(

            message =
                """
                I can help you explore the campus and find faculty information.

                You can ask me things like:

                • "Tell me about Dr. XYZ"
                • "What does Dr. XYZ teach?"
                • "What is Dr. XYZ researching?"
                • "What is Dr. XYZ's qualification?"
                • "What is Dr. XYZ's designation?"
                • "How much experience does Dr. XYZ have?"
                • "What awards has Dr. XYZ received?"
                • "How many publications does Dr. XYZ have?"
                • "What is Dr. XYZ's email?"
                • "Where is Dr. XYZ?"
                
                I can also help you find faculty based on their courses or research areas.
                """.trimIndent(),

            hasFacultyInformation = false
        )
    }

    // =================================================================
    // UNKNOWN
    // =================================================================

    private fun createUnknownResponse(): TracerBotResponse {

        return TracerBotResponse(

            message =
                "I'm sorry, I didn't quite understand that. " +
                        "Try asking me about a faculty member, " +
                        "their courses, research, qualification, " +
                        "contact information, or location.",

            hasFacultyInformation = false
        )
    }

    // =================================================================
    // NO FACULTY
    // =================================================================

    private fun createNoFacultyResponse(
        query: TracerBotQuery
    ): TracerBotResponse {

        return TracerBotResponse(

            message =
                "I couldn't find a faculty member matching " +
                        "\"${query.originalText}\". " +
                        "Please try the faculty member's name " +
                        "or ask about a course or research area.",

            hasFacultyInformation = false
        )
    }

    // =================================================================
    // MULTIPLE FACULTY
    // =================================================================

    private fun createMultipleFacultyResponse(
        query: TracerBotQuery
    ): TracerBotResponse {

        val matches = query.facultyMatches

        val message = buildString {

            append("I found these faculty members that may match ")
            append("your question:\n\n")

            matches
                .take(5)
                .forEach { faculty ->

                    append("• ")
                    append(faculty.name)

                    if (faculty.designation.isNotBlank()) {

                        append(" — ")
                        append(faculty.designation)
                    }

                    append("\n")
                }

            if (matches.size > 5) {

                append("\nThere are additional matches. ")
                append("Please provide a faculty name for a more specific answer.")
            }
        }

        return TracerBotResponse(

            message = message.toString().trim(),

            facultyName = null,

            facultyMatches = matches,

            hasFacultyInformation = true
        )
    }

    // =================================================================
    // COMMON FACULTY RESPONSE
    // =================================================================

    private fun facultyResponse(
        message: String,
        faculty: Faculty
    ): TracerBotResponse {

        return TracerBotResponse(

            message = message,

            facultyName = faculty.name,

            facultyMatches = listOf(faculty),

            showNavigationAction = false,

            navigationDestination = null,

            hasFacultyInformation = true
        )
    }
}