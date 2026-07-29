package com.tracer.kiosk.presentation.components.idle

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object IdleManager {

    private val _lastInteraction = MutableStateFlow(System.currentTimeMillis())
    val lastInteraction: StateFlow<Long> = _lastInteraction.asStateFlow()

    fun resetTimer() {
        _lastInteraction.value = System.currentTimeMillis()
    }

}