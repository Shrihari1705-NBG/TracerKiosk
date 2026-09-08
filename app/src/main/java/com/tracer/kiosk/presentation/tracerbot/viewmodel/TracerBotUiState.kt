package com.tracer.kiosk.presentation.tracerbot.viewmodel

import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import com.tracer.kiosk.presentation.tracerbot.response.TracerBotResponse

data class TracerBotUiState(

    val query: String = "",

    val response: TracerBotResponse? = null,

    val facultyMatches: List<Faculty> = emptyList(),

    val isLoading: Boolean = false,

    val showBot: Boolean = false,

    val errorMessage: String? = null
)