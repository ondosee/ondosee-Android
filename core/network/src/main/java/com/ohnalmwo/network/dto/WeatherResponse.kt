package com.ohnalmwo.network.dto

import com.ohnalmwo.model.enum.Significant
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weathers: List<WeatherDetailResponse>
)

@Serializable
data class WeatherDetailResponse(
    val significant: Significant,
    val timeZone: List<TimeZoneInfoResponse>
)

@Serializable
data class TimeZoneInfoResponse(
    val time: LocalTime,
    val value: String
)