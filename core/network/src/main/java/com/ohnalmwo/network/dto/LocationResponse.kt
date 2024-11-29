package com.ohnalmwo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val page: PageInfoResponse,
    val locations: List<LocationInfoResponse>
)

@Serializable
data class PageInfoResponse(
    val total: Int,
    val current: Int
)

@Serializable
data class LocationInfoResponse(
    val title: String,
    val x: String,
    val y: String,
)