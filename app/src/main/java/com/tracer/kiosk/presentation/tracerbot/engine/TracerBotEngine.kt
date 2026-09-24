package com.tracer.kiosk.presentation.tracerbot.engine

import com.tracer.kiosk.presentation.tracerbot.context.TracerBotConversationContext
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
 * The engine also maintains lightweight conversation context
 * so TracerBot can understand follow-up questions.
 *
 * All processing is local.
 */
class TracerBotEngine(
    private val facultyRepository: FacultyRepository
) {

    // ---------------------------------------------------------
    // Conversation Context
    // ---------------------------------------------------------

    private var conversationContext =
        TracerBotConversationContext()

    // ---------------------------------------------------------
    // Query Processor
    // ---------------------------------------------------------

    private fun createQueryProcessor(
        facultyRepository: FacultyRepository
    ): TracerBotQueryProcessor {

        return TracerBotQueryProcessor(
            facultyRepository = facultyRepository,
            conversationContext = conversationContext
        )
    }

    // ---------------------------------------------------------
    // Response Engine
    // ---------------------------------------------------------

    private val responseEngine =
        TracerBotResponseEngine()

    /**
     * Process a complete user question.
     *
     * The query processor receives the current conversation
     * context so follow-up questions can eventually refer
     * to information from the previous query.
     */
    fun ask(
        question: String
    ): TracerBotResponse {

        // -----------------------------------------------------
        // Step 1 — Process the query
        // -----------------------------------------------------

        val queryProcessor =
            createQueryProcessor(facultyRepository)

        val processedQuery =
            queryProcessor.process(question)

        // -----------------------------------------------------
        // Step 2 — Generate the response
        // -----------------------------------------------------

        val response =
            responseEngine.generateResponse(
                query = processedQuery
            )

        // -----------------------------------------------------
        // Step 3 — Update conversation context
        // -----------------------------------------------------

        conversationContext =
            conversationContext.copy(
                lastFaculty = processedQuery.faculty
                    ?: conversationContext.lastFaculty,

                lastIntent = processedQuery.intent
            )

        return response
    }

    /**
     * Clear the current conversation context.
     *
     * This is useful when the user starts a completely
     * new conversation.
     */
    fun clearConversation() {

        conversationContext =
            TracerBotConversationContext()
    }
}