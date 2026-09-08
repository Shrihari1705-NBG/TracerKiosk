package com.tracer.kiosk.presentation.tracerbot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TracerBotViewModel(
    private val tracerBotEngine: TracerBotEngine
) : ViewModel() {

    private val _uiState = MutableStateFlow(TracerBotUiState())

    val uiState: StateFlow<TracerBotUiState> =
        _uiState.asStateFlow()

    /**
     * Update the current user query.
     */
    fun updateQuery(query: String) {

        _uiState.value = _uiState.value.copy(
            query = query,
            errorMessage = null
        )
    }

    /**
     * Process the current user query.
     */
    fun submitQuery() {

        val query = _uiState.value.query.trim()

        if (query.isBlank()) {
            return
        }

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {

                val result = tracerBotEngine.ask(query)

                _uiState.value = _uiState.value.copy(
                    query = query,
                    response = result,
                    facultyMatches = result.facultyMatches,
                    isLoading = false,
                    errorMessage = null
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage =
                        "Sorry, I couldn't process that question."
                )
            }
        }
    }

    /**
     * Open the TracerBot assistant.
     */
    fun openBot() {

        _uiState.value = _uiState.value.copy(
            showBot = true
        )
    }

    /**
     * Close the TracerBot assistant.
     */
    fun closeBot() {

        _uiState.value = _uiState.value.copy(
            showBot = false
        )
    }

    /**
     * Clear the current conversation/query.
     */
    fun clearQuery() {

        _uiState.value = TracerBotUiState(
            showBot = _uiState.value.showBot
        )
    }
}