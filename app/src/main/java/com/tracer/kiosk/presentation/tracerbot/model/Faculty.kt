package com.tracer.kiosk.presentation.tracerbot.model

data class Faculty(
    val name: String,
    val designation: String,
    val qualification: String,
    val department: String,
    val about: String,
    val researchInterests: List<String>,
    val coursesTaught: List<String>,
    val selectedRecognition: List<String>,
    val experience: String,
    val publications: Int?,
    val email: String
)