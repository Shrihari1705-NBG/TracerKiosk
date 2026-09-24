package com.tracer.kiosk.presentation.tracerbot.model

/**
 * Represents one message in the TracerBot conversation.
 */
data class TracerBotMessage(
    val text: String,
    val sender: Sender
) {

    enum class Sender {
        USER,
        BOT
    }
}
