package com.tracer.kiosk.presentation.tracerbot.data

import android.content.Context
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import org.json.JSONArray

class FacultyRepository(
    private val context: Context
) {

    private var facultyList: List<Faculty> = emptyList()

    /**
     * Load all faculty information from:
     *
     * assets/tracerbot/faculty.json
     */
    fun loadFaculty(): List<Faculty> {

        // Return cached data if already loaded
        if (facultyList.isNotEmpty()) {
            return facultyList
        }

        return try {

            val jsonString = context.assets
                .open("tracerbot/faculty.json")
                .bufferedReader()
                .use { it.readText() }

            val jsonArray = JSONArray(jsonString)

            val result = mutableListOf<Faculty>()

            for (i in 0 until jsonArray.length()) {

                val jsonObject = jsonArray.getJSONObject(i)

                val faculty = Faculty(

                    name = jsonObject.optString("name"),

                    designation = jsonObject.optString("designation"),

                    qualification = jsonObject.optString("qualification"),

                    department = jsonObject.optString("department"),

                    about = jsonObject.optString("about"),

                    researchInterests =
                        getStringList(
                            jsonObject.optJSONArray("researchInterests")
                        ),

                    coursesTaught =
                        getStringList(
                            jsonObject.optJSONArray("coursesTaught")
                        ),

                    selectedRecognition =
                        getStringList(
                            jsonObject.optJSONArray("selectedRecognition")
                        ),

                    experience = jsonObject.optString("experience"),

                    publications =
                        if (
                            jsonObject.has("publications") &&
                            !jsonObject.isNull("publications")
                        ) {
                            jsonObject.optInt("publications")
                        } else {
                            null
                        },

                    email = jsonObject.optString("email")
                )

                result.add(faculty)
            }

            facultyList = result

            facultyList

        } catch (e: Exception) {

            e.printStackTrace()

            emptyList()
        }
    }

    /**
     * Get all faculty members.
     */
    fun getAllFaculty(): List<Faculty> {
        return loadFaculty()
    }

    /**
     * Find a faculty member by name.
     *
     * Example:
     *
     * "Dr. Plasin Francis Dias"
     * "Plasin Francis"
     * "Francis Dias"
     */
    fun findFacultyByName(name: String): Faculty? {

        val searchName = name
            .trim()
            .lowercase()

        if (searchName.isBlank()) {
            return null
        }

        return loadFaculty().firstOrNull { faculty ->

            faculty.name
                .lowercase()
                .contains(searchName)
        }
    }

    /**
     * Search faculty information.
     *
     * Searches through:
     *
     * - Name
     * - Designation
     * - Qualification
     * - Department
     * - About
     * - Research interests
     * - Courses taught
     * - Recognition
     * - Experience
     * - Email
     */
    fun searchFaculty(query: String): List<Faculty> {

        val searchQuery = query
            .trim()
            .lowercase()

        if (searchQuery.isBlank()) {
            return emptyList()
        }

        return loadFaculty().filter { faculty ->

            val searchableText = buildString {

                append(faculty.name)
                append(" ")

                append(faculty.designation)
                append(" ")

                append(faculty.qualification)
                append(" ")

                append(faculty.department)
                append(" ")

                append(faculty.about)
                append(" ")

                faculty.researchInterests.forEach {
                    append(it)
                    append(" ")
                }

                faculty.coursesTaught.forEach {
                    append(it)
                    append(" ")
                }

                faculty.selectedRecognition.forEach {
                    append(it)
                    append(" ")
                }

                append(faculty.experience)
                append(" ")

                append(faculty.publications ?: "")
                append(" ")

                append(faculty.email)
            }

            searchableText
                .lowercase()
                .contains(searchQuery)
        }
    }

    /**
     * Clear the cached data.
     *
     * Useful during development when faculty.json
     * is changed while the application is running.
     */
    fun clearCache() {
        facultyList = emptyList()
    }

    /**
     * Convert a JSON array into a Kotlin List<String>.
     */
    private fun getStringList(
        jsonArray: JSONArray?
    ): List<String> {

        if (jsonArray == null) {
            return emptyList()
        }

        val result = mutableListOf<String>()

        for (i in 0 until jsonArray.length()) {

            val value = jsonArray.optString(i)

            if (value.isNotBlank()) {
                result.add(value)
            }
        }

        return result
    }
}