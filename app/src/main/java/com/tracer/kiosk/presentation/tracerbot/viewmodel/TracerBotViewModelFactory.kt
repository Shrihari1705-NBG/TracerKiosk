package com.tracer.kiosk.presentation.tracerbot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine

class TracerBotViewModelFactory(
    private val tracerBotEngine: TracerBotEngine
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(TracerBotViewModel::class.java)) {

            return TracerBotViewModel(
                tracerBotEngine = tracerBotEngine
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}