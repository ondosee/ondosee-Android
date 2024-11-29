package com.ohnalmwo.main.util

import com.ohnalmwo.model.TimeZoneInfo
import com.ohnalmwo.model.WeatherDetail
import com.ohnalmwo.model.enum.ForecastType
import com.ohnalmwo.ui.toKoreanHourFormat
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlin.time.Duration.Companion.hours

fun ForecastType.isShowCard(weather: WeatherDetail): Boolean {
    return when (this) {
        ForecastType.RAIN -> weather.timeZone.any { it.value.toInt() >= 30 }
        ForecastType.FINE_DUST -> weather.timeZone.any { it.value.toInt() >= 41 }
        ForecastType.ULTRAFINE_DUST -> weather.timeZone.any { it.value.toInt() >= 21 }
        else -> false
    }
}

fun ForecastType.createDescriptionText(weather: WeatherDetail): String {
    val threshold = when (this) {
        ForecastType.RAIN -> 30
        ForecastType.FINE_DUST -> 41
        ForecastType.ULTRAFINE_DUST -> 21
        else -> return ""
    }

    val ranges = findContinuousRanges(weather.timeZone, threshold)

    return if (ranges.isNotEmpty()) {
        val range = ranges.first()

        "${range.start.toKoreanHourFormat()}부터 ${range.duration}시간 동안 ${this.getDescription()}"
    } else {
        ""
    }
}

fun ForecastType.getDescription(): String {
    return when (this) {
        ForecastType.RAIN -> "비가 올 예정이에요.\n우산을 챙겨가세요."
        ForecastType.FINE_DUST -> "미세먼지가 심해요.\n마스크를 가져가세요."
        ForecastType.ULTRAFINE_DUST -> "초미세먼지가 심해요.\n마스크를 가져가세요."
        else -> ""
    }
}

data class TimeRange(val start: LocalTime, val duration: Int)

fun findContinuousRanges(timeZoneInfos: List<TimeZoneInfo>, threshold: Int): List<TimeRange> {
    val ranges = mutableListOf<TimeRange>()
    var start: LocalTime? = null
    var duration = 0

    timeZoneInfos.forEach{ info ->
        if (info.value.toInt() >= threshold) {
            if (start == null) {
                start = info.time
            }
            duration++
        } else {
            if (start != null) {
                ranges.add(TimeRange(start!!, duration))
                start = null
                duration = 0
            }
        }
    }

    if (start != null) {
        ranges.add(TimeRange(start!!, duration))
    }

    return ranges
}