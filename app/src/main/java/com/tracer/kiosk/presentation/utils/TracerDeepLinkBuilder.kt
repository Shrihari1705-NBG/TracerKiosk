package com.tracer.kiosk.presentation.utils

object TracerDeepLinkBuilder {

    private const val SCHEME = "tracer"
    private const val HOST = "navigate"
    private const val VERSION = "1.5"

    /**
     * Builds the deep link that will be encoded into the QR code.
     *
     */
    fun build(
        destinationId: String,
        startNode: String = "N1",
        floor: String = "ground"
    ): String {

        return "$SCHEME://$HOST?start=$startNode&destination=$destinationId&floor=$floor&version=$VERSION"
    }

    /**
     * Converts a human-readable destination name into a URL-safe id.
     */
    fun destinationIdFromName(name: String): String {
        return name
            .lowercase()
            .replace(".", "")
            .replace("-", " ")
            .replace("&", "and")
            .trim()
            .replace(Regex("\\\\s+"), "_")
    }
}