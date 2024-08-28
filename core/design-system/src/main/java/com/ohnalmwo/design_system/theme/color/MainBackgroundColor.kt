package com.ohnalmwo.design_system.theme.color

import androidx.compose.ui.graphics.Color
import com.ohnalmwo.model.enum.MainBackgroundColorType

object MainBackgroundColor {
    val RAIN: List<Color> = listOf(Color(0xFF7C8FA9), Color(0xFF2A3441))
    val SNOW: List<Color> = listOf(Color(0xFFA6C5F1), Color(0xFF72ABF6))
    val THUNDER: List<Color> = listOf(Color(0xFF43413D), Color(0xFF000B1A))
    val WORST: List<Color> = listOf(Color(0xFF4D4D4D), Color(0xFF1A1A1A))
    val MISE_TOO_BAD: List<Color> = listOf(Color(0xFFE07652), Color(0xFF802000))
    val MISE_SO_BAD: List<Color> = listOf(Color(0xFFE09952), Color(0xFF804000))
    val MISE_BAD: List<Color> = listOf(Color(0xFFE0BD52), Color(0xFF806000))
    val HEAT_WAVE: List<Color> = listOf(Color(0xFFF5883D), Color(0xFF993F00))
    val HEAVE_SNOW: List<Color> = listOf(Color(0xFFA6C5F1), Color(0xFF72ABF6))

    var mainBackground: List<Color> = RAIN

    fun updateMainBackground(type: MainBackgroundColorType) {
        mainBackground = when(type) {
            MainBackgroundColorType.RAIN -> RAIN
            MainBackgroundColorType.SNOW -> SNOW
            MainBackgroundColorType.THUNDER -> THUNDER
            MainBackgroundColorType.WORST -> WORST
            MainBackgroundColorType.MISE_TOO_BAD -> MISE_TOO_BAD
            MainBackgroundColorType.MISE_SO_BAD -> MISE_SO_BAD
            MainBackgroundColorType.MISE_BAD -> MISE_BAD
            MainBackgroundColorType.HEAT_WAVE -> HEAT_WAVE
            MainBackgroundColorType.HEAVE_SNOW -> HEAVE_SNOW
        }
    }
}