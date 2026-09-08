package com.tracer.kiosk.presentation.tracerbot.query

import com.tracer.kiosk.presentation.tracerbot.intent.TracerBotIntent
import com.tracer.kiosk.presentation.tracerbot.model.Faculty

/**
 * Represents the result of interpreting a user's question.
 *
 * This object connects:
 *
 * User question
 *      +
 * Detected intent
 *      +
 * Matched faculty
 *      +
 * Search confidence
 */
data class TracerBotQuery(

    /**
     * The original text entered/spoken by the user.
     */
    val originalText: String,

    /**
     * What the user is trying to do.
     */
    val intent: TracerBotIntent,

    /**
     * Faculty member identified from the question.
     *
     * Null when the question is not related to
     * a specific faculty member.
     */
    val faculty: Faculty? = null,

    /**
     * Other faculty members that matched the query.
     *
     * This is useful when the user asks something like:
     *
     * "Who teaches VLSI?"
     */
    val facultyMatches: List<Faculty> = emptyList(),

    /**
     * Relevance/confidence score for the primary match.
     *
     * This is not an AI probability.
     * It is a local search score.
     */
    val confidenceScore: Int = 0,

    /**
     * Whether the query should lead to navigation.
     */
    val requiresNavigation: Boolean = false
)