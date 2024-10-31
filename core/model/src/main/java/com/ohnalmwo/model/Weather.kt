package com.ohnalmwo.model

import androidx.compose.runtime.Immutable
import com.ohnalmwo.model.enum.Significant
import kotlinx.datetime.LocalTime

@Immutable
data class Weather(
    val weathers: List<WeatherDetail>
) {
    companion object {
        fun default() = Weather(
            weathers = listOf(WeatherDetail.default())
        )
    }
}

@Immutable
data class WeatherDetail(
    val significant: Significant,
    val timeZone: List<TimeZoneInfo>
) {
    companion object {
        fun default() = WeatherDetail(
            significant = Significant.BEST_10,
            timeZone = listOf(TimeZoneInfo.default())
        )
    }
}

@Immutable
data class TimeZoneInfo(
    val time: LocalTime,
    val value: String
) {
    companion object {
        fun default() = TimeZoneInfo(
            time = LocalTime(0, 0, 0),
            value = ""
        )
    }
}