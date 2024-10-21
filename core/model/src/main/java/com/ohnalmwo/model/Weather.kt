package com.ohnalmwo.model

import androidx.compose.runtime.Immutable
import com.ohnalmwo.model.enum.Significant
import kotlinx.datetime.LocalTime

@Immutable
data class Weather(
    val weathers: List<WeatherDetail>
)

@Immutable
data class WeatherDetail(
    val significant: Significant,
    val timeZone: List<TimeZoneInfo>
)

@Immutable
data class TimeZoneInfo(
    val time: LocalTime,
    val value: String
)