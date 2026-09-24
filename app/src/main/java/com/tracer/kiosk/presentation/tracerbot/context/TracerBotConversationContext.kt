package com.tracer.kiosk.presentation.tracerbot.context

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent
import com.tracer.kiosk.presentation.tracerbot.model.Faculty

data class TracerBotConversationContext(
    val lastFaculty: Faculty? = null,
    val lastIntent: TracerBotIntent? = null
)