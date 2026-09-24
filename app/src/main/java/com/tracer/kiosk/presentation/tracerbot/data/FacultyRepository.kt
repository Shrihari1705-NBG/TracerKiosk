package com.tracer.kiosk.presentation.tracerbot.data

import android.content.Context
import android.util.Log
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import org.json.JSONArray
import org.json.JSONObject

class FacultyRepository(
    private val context: Context
) {

    companion object {
        private const val TAG = "FacultyRepository"
        private const val FACULTY_ASSET_PATH = "tracerbot/faculty.json"
    }

    private var facultyList: List<Faculty> = emptyList()

    /**
     * Load all faculty information from:
     *
     * assets/tracerbot/faculty.json
     *
     * Supported JSON formats:
     *
     * 1. Top-level array:
     *
     * [
     *   {
     *     "name": "..."
     *   }
     * ]
     *
     * 2. Object containing a faculty array:
     *
     * {
     *   "faculty": [
     *     {
     *       "name": "..."
     *     }
     *   ]
     * }
     */
    fun loadFaculty(): List<Faculty> {

        // Return cached data if already loaded
        if (facultyList.isNotEmpty()) {
            return facultyList
        }

        return try {

            // -----------------------------------------------------
            // Read JSON file
            // -----------------------------------------------------

            val jsonString = context.assets
                .open(FACULTY_ASSET_PATH)
                .bufferedReader()
                .use { it.readText() }
                .trim()

            if (jsonString.isBlank()) {

                Log.e(
                    TAG,
                    "faculty.json is empty."
                )

                return emptyList()
            }

            Log.d(
                TAG,
                "faculty.json loaded successfully."
            )

            // -----------------------------------------------------
            // Determine JSON structure
            // -----------------------------------------------------

            val jsonArray = when {

                jsonString.startsWith("[") -> {

                    JSONArray(jsonString)
                }

                jsonString.startsWith("{") -> {

                    val jsonObject = JSONObject(jsonString)

                    jsonObject.optJSONArray("faculty")
                        ?: jsonObject.optJSONArray("faculties")
                        ?: JSONArray()
                }

                else -> {

                    Log.e(
                        TAG,
                        "Invalid JSON format in faculty.json."
                    )

                    return emptyList()
                }
            }

            // -----------------------------------------------------
            // Convert JSON to Faculty objects
            // -----------------------------------------------------

            val result = mutableListOf<Faculty>()

            for (i in 0 until jsonArray.length()) {

                val jsonObject = jsonArray.optJSONObject(i)

                if (jsonObject == null) {
                    continue
                }

                val faculty = Faculty(

                    name = jsonObject.optString(
                        "name"
                    ),

                    designation = jsonObject.optString(
                        "designation"
                    ),

                    qualification = jsonObject.optString(
                        "qualification"
                    ),

                    department = jsonObject.optString(
                        "department"
                    ),

                    about = jsonObject.optString(
                        "about"
                    ),

                    researchInterests =
                        getStringList(
                            jsonObject.optJSONArray(
                                "research_interests"
                            )
                        ),

                    coursesTaught =
                        getStringList(
                            jsonObject.optJSONArray(
                                "courses_taught"
                            )
                        ),

                    selectedRecognition =
                        getStringList(
                            jsonObject.optJSONArray(
                                "recognition"
                            )
                        ),

                    experience = jsonObject.optString(
                        "experience"
                    ),

                    publications =
                        if (
                            jsonObject.has("publications") &&
                            !jsonObject.isNull("publications")
                        ) {
                            jsonObject.optInt(
                                "publications"
                            )
                        } else {
                            null
                        },

                    email = jsonObject.optString(
                        "email"
                    )
                )

                // Only add records that actually contain a name.
                if (faculty.name.isNotBlank()) {
                    result.add(faculty)
                }
            }

            // -----------------------------------------------------
            // Cache result
            // -----------------------------------------------------

            facultyList = result

            Log.d(
                TAG,
                "Loaded ${facultyList.size} faculty members."
            )

            if (facultyList.isNotEmpty()) {

                Log.d(
                    TAG,
                    "First faculty: ${facultyList.first().name}"
                )
            }

            facultyList

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Failed to load faculty.json",
                e
            )

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
     */
    fun findFacultyByName(
        name: String
    ): Faculty? {

        val searchName = normalize(name)

        if (searchName.isBlank()) {
            return null
        }

        return loadFaculty().firstOrNull { faculty ->

            normalize(faculty.name)
                .contains(searchName)
        }
    }

    /**
     * Search faculty information.
     */
    fun searchFaculty(
        query: String
    ): List<Faculty> {

        val searchQuery = normalize(query)

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

                append(
                    faculty.publications ?: ""
                )

                append(" ")

                append(faculty.email)
            }

            normalize(searchableText)
                .contains(searchQuery)
        }
    }

    /**
     * Clear the cached data.
     */
    fun clearCache() {
        facultyList = emptyList()

        Log.d(
            TAG,
            "Faculty cache cleared."
        )
    }

    /**
     * Convert a JSON array into List<String>.
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

    /**
     * Normalize text for searching.
     */
    private fun normalize(
        text: String
    ): String {

        return text
            .lowercase()
            .replace(
                Regex("[^a-z0-9@.+-]"),
                " "
            )
            .replace(
                Regex("\\s+"),
                " "
            )
            .trim()
    }
}