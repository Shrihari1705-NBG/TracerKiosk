package com.tracer.kiosk.presentation.feature.navigation.model

data class Destination(

    val id: String,

    val name: String,

    val category: DestinationCategory,

    val nodeId: String,

    val aliases: List<String> = emptyList()

)