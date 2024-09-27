package com.goms.design_system.theme

import androidx.compose.ui.graphics.Color

abstract class ColorTheme {
    //System Colors
    abstract val PRIMARY: Color
    abstract val WARNING: Color
    abstract val ABLE: Color
    abstract val DISABLE: Color
    abstract val SECONDARY: Color
    abstract val TERTIARY: Color

    //Background Colors
    abstract val BACKGROUND_RAIN: List<Color>
    abstract val BACKGROUND_SNOW: List<Color>
    abstract val BACKGROUND_THUNDER: List<Color>
    abstract val BACKGROUND_WORST: List<Color>
    abstract val BACKGROUND_MISE_TOO_BAD: List<Color>
    abstract val BACKGROUND_MISE_SO_BAD: List<Color>
    abstract val BACKGROUND_MISE_BAD: List<Color>
    abstract val BACKGROUND_HEAT_WAVE: List<Color>
    abstract val BACKGROUND_HEAVE_SNOW: List<Color>

    //Component Colors
    abstract val RAIN: Color
    abstract val SNOW: Color
    abstract val VERY_GOOD: Color
    abstract val SO_GOOD: Color
    abstract val GOOD: Color
    abstract val SOSO: Color
    abstract val BAD: Color
    abstract val SO_BAD: Color
    abstract val TOO_BAD: Color
    abstract val WORST: Color

    abstract val THEME_BLACK: Color
    abstract val THEME_WHITE: Color

    abstract val BLACK: Color
    abstract val WHITE: Color

    abstract val BACKGROUND: Color
}