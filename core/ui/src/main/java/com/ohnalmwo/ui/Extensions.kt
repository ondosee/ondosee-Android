package com.ohnalmwo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.Significant
import com.ohnalmwo.design_system.R
import com.ohnalmwo.model.enum.ForecastType
import kotlinx.datetime.LocalTime

@Composable
fun Significant.getBackgroundColors(): List<Color> {
    return when (this) {
        Significant.HEAT_WAVE -> colors.BACKGROUND_HEAT_WAVE
        Significant.COLD_WAVE -> colors.BACKGROUND_HEAVE_SNOW
        Significant.DROUGHT -> colors.BACKGROUND_CLEAR
        Significant.SNOW -> colors.BACKGROUND_SNOW
        Significant.RAIN -> colors.BACKGROUND_RAIN
        Significant.GALE -> colors.BACKGROUND_CLEAR
        Significant.BEST_10, Significant.BEST_25,
        Significant.GOOD_10, Significant.GOOD_25,
        Significant.FAIR_10, Significant.FAIR_25 -> colors.BACKGROUND_CLEAR
        Significant.AVERAGE_10, Significant.AVERAGE_25 -> colors.BACKGROUND_MISE_BAD
        Significant.POOR_10, Significant.POOR_25 -> colors.BACKGROUND_MISE_SO_BAD
        Significant.BAD_10, Significant.BAD_25 -> colors.BACKGROUND_MISE_TOO_BAD
        Significant.WORST_10, Significant.WORST_25 -> colors.BACKGROUND_WORST
    }
}

@Composable
fun Significant.getComponentColors(): Color {
    return when (this) {
        Significant.SNOW -> colors.SNOW
        Significant.RAIN -> colors.RAIN
        Significant.BEST_10, Significant.BEST_25 -> colors.VERY_GOOD
        Significant.GOOD_10, Significant.GOOD_25 -> colors.SO_GOOD
        Significant.FAIR_10, Significant.FAIR_25 -> colors.GOOD
        Significant.AVERAGE_10, Significant.AVERAGE_25 -> colors.BAD
        Significant.POOR_10, Significant.POOR_25 -> colors.SO_BAD
        Significant.BAD_10, Significant.BAD_25 -> colors.TOO_BAD
        Significant.WORST_10, Significant.WORST_25 -> colors.WORST
        else -> colors.WHITE
    }
}

fun Significant.getSignificantWeatherText(data: String): String {
    return when (this) {
        Significant.HEAT_WAVE -> data.appendCelsius()
        Significant.COLD_WAVE -> data.appendCelsius()
        Significant.DROUGHT -> ""
        Significant.SNOW -> data.appendPercent()
        Significant.RAIN -> data.appendPercent()
        Significant.GALE -> ""
        Significant.BEST_10, Significant.BEST_25,
        Significant.GOOD_10, Significant.GOOD_25,
        Significant.FAIR_10, Significant.FAIR_25 -> "Good"
        Significant.AVERAGE_10, Significant.POOR_10, Significant.BAD_10, Significant.WORST_10 -> data.appendMicrograms()
        Significant.AVERAGE_25, Significant.POOR_25, Significant.BAD_25, Significant.WORST_25 -> data.appendMicrogramsPerCubicMeter()
    }
}

fun Significant.getAnimationLottie(): Int {
    return when (this) {
        Significant.HEAT_WAVE -> R.raw.rainy
        Significant.COLD_WAVE -> R.raw.rainy
        Significant.DROUGHT -> R.raw.rainy
        Significant.SNOW -> R.raw.rainy
        Significant.RAIN -> R.raw.rainy
        Significant.GALE -> R.raw.rainy
        Significant.BEST_10, Significant.BEST_25 -> R.raw.rainy
        Significant.GOOD_10, Significant.GOOD_25 -> R.raw.rainy
        Significant.FAIR_10, Significant.FAIR_25 -> R.raw.rainy
        Significant.AVERAGE_10, Significant.AVERAGE_25 -> R.raw.rainy
        Significant.POOR_10, Significant.POOR_25 -> R.raw.rainy
        Significant.BAD_10, Significant.BAD_25 -> R.raw.rainy
        Significant.WORST_10, Significant.WORST_25 -> R.raw.rainy
    }
}

fun Significant.getWeatherConditionText(): String {
    return when (this) {
        Significant.HEAT_WAVE -> "폭염주의보 발효"
        Significant.COLD_WAVE -> "한파주의보 발효"
        Significant.DROUGHT -> ""
        Significant.SNOW -> "눈이 올 것 같아요!"
        Significant.RAIN -> "비가 올 것 같아요!"
        Significant.GALE -> ""
        Significant.BEST_10, Significant.BEST_25,
        Significant.GOOD_10, Significant.GOOD_25,
        Significant.FAIR_10, Significant.FAIR_25 -> "아주 화창한 날이에요!"
        Significant.AVERAGE_10, Significant.AVERAGE_25 -> "미세먼지 보통"
        Significant.POOR_10, Significant.POOR_25 -> "미세먼지 나쁨"
        Significant.BAD_10, Significant.BAD_25 -> "미세먼지 매우나쁨"
        Significant.WORST_10, Significant.WORST_25 -> "미세먼지 최악"
    }
}

fun Significant.getWeatherConditionDescriptionText(): String {
    return when (this) {
        Significant.HEAT_WAVE -> "외출을 자제하고 물을 충분히 준비하세요!"
        Significant.COLD_WAVE -> "장갑과 두꺼운 외투가 필수에요!"
        Significant.DROUGHT -> ""
        Significant.SNOW -> "우산이나 장갑을 챙겨가세요!"
        Significant.RAIN -> "우산을 미리 챙겨가세요!"
        Significant.GALE -> ""
        Significant.BEST_10, Significant.BEST_25,
        Significant.GOOD_10, Significant.GOOD_25,
        Significant.FAIR_10, Significant.FAIR_25 -> "나쁜 날씨가 없네요, 축하해요!"
        Significant.AVERAGE_10, Significant.AVERAGE_25 -> "민감하신 분이라면 마스크가 필요해요."
        Significant.POOR_10, Significant.POOR_25,
        Significant.BAD_10, Significant.BAD_25 -> "마스크를 꼭 챙겨가세요!"
        Significant.WORST_10, Significant.WORST_25 -> "외출을 자제하시고 마스크를 꼭 챙겨가세요!"
    }
}

fun Significant.getForecastType(): ForecastType {
    return when (this) {
        Significant.HEAT_WAVE,
        Significant.COLD_WAVE,
        Significant.DROUGHT,
        Significant.GALE -> ForecastType.TEMPERATURE
        Significant.SNOW,
        Significant.RAIN -> ForecastType.RAIN
        Significant.BEST_10,
        Significant.GOOD_10,
        Significant.FAIR_10,
        Significant.AVERAGE_10,
        Significant.POOR_10,
        Significant.BAD_10,
        Significant.WORST_10 -> ForecastType.FINE_DUST
        Significant.BEST_25,
        Significant.GOOD_25,
        Significant.FAIR_25,
        Significant.AVERAGE_25,
        Significant.POOR_25,
        Significant.BAD_25,
        Significant.WORST_25 -> ForecastType.ULTRAFINE_DUST
    }
}

fun LocalTime.toKoreanHourFormat(): String {
    val hour = when {
        this.hour == 0 -> 12
        this.hour > 12 -> this.hour - 12
        else -> this.hour
    }

    val amPm = if (this.hour < 12) "오전" else "오후"

    return "$amPm ${hour}시"
}

fun String.appendMicrograms(): String = "${this}μg"

fun String.appendMicrogramsPerCubicMeter(): String = "${this}μg/m³"

fun String.appendPercent(): String = "${this}%"

fun String.appendCelsius(): String = "${this}°C"