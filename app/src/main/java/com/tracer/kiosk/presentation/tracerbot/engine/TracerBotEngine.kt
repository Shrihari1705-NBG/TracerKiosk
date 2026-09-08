package com.tracer.kiosk.presentation.tracerbot.engine

import com.tracer.kiosk.presentation.tracerbot.data.FacultyRepository
import com.tracer.kiosk.presentation.tracerbot.processor.TracerBotQueryProcessor
import com.tracer.kiosk.presentation.tracerbot.response.TracerBotResponse
import com.tracer.kiosk.presentation.tracerbot.response.TracerBotResponseEngine

/**
 * Main entry point for the TracerBot processing system.
 *
 * This class connects:
 *
 * User Query
 *      ↓
 * Query Processor
 *      ↓
 * Response Engine
 *      ↓
 * Final Response
 *
 * The UI should eventually communicate with this class
 * instead of directly communicating with the individual
 * TracerBot components.
 *
 * All processing is local.
 */
class TracerBotEngine(
    facultyRepository: FacultyRepository
) {

    // ---------------------------------------------------------
    // Query Processor
    // ---------------------------------------------------------

    private val queryProcessor =
        TracerBotQueryProcessor(
            facultyRepository = facultyRepository
        )

    // ---------------------------------------------------------
    // Response Engine
    // ---------------------------------------------------------

    private val responseEngine =
        TracerBotResponseEngine()

    /**
     * Process a complete user question.
     *
     * Example:
     *
     * "Tell me about Prof. XYZ"
     *
     * The method:
     *
     * 1. Processes the user's query.
     * 2. Finds relevant faculty information.
     * 3. Generates a user-friendly response.
     * 4. Returns the final TracerBotResponse.
     */
    fun ask(
        question: String
    ): TracerBotResponse {

        // -----------------------------------------------------
        // Step 1 — Process the query
        // -----------------------------------------------------

        val processedQuery =
            queryProcessor.process(question)

        // -----------------------------------------------------
        // Step 2 — Generate the response
        // -----------------------------------------------------

        return responseEngine.generateResponse(
            query = processedQuery
        )
    }
}