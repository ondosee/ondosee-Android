package com.ohnalmwo.design_system.theme.color

import androidx.compose.ui.graphics.Color
import com.goms.design_system.theme.ColorTheme

object DarkColor : ColorTheme() {
    override val PRIMARY = Color(0xFF0095FF)
    override val WARNING = Color(0xFFFF3333)
    override val ABLE = Color(0xFF26D926)
    override val DISABLE = Color(0xFFCCCCCC)
    override val SECONDARY = Color(0xBFFFFFFF)
    override val TERTIARY = Color(0x80FFFFFF)

    override val BACKGROUND_RAIN: List<Color> = listOf(Color(0xFF7C8FA9), Color(0xFF2A3441))
    override val BACKGROUND_SNOW: List<Color> = listOf(Color(0xFFA6C5F1), Color(0xFF72ABF6))
    override val BACKGROUND_THUNDER: List<Color> = listOf(Color(0xFF43413D), Color(0xFF000B1A))
    override val BACKGROUND_WORST: List<Color> = listOf(Color(0xFF4D4D4D), Color(0xFF1A1A1A))
    override val BACKGROUND_MISE_TOO_BAD: List<Color> = listOf(Color(0xFFE07652), Color(0xFF802000))
    override val BACKGROUND_MISE_SO_BAD: List<Color> = listOf(Color(0xFFE09952), Color(0xFF804000))
    override val BACKGROUND_MISE_BAD: List<Color> = listOf(Color(0xFFE0BD52), Color(0xFF806000))
    override val BACKGROUND_HEAT_WAVE: List<Color> = listOf(Color(0xFFF5883D), Color(0xFF993F00))
    override val BACKGROUND_HEAVE_SNOW: List<Color> = listOf(Color(0xFFA6C5F1), Color(0xFF72ABF6))

    override val RAIN = Color(0xFF33CCFF)
    override val SNOW = Color(0xFFFFFFFF)
    override val VERY_GOOD = Color(0xFF4D97FF)
    override val SO_GOOD = Color(0xFF4DD2FF)
    override val GOOD = Color(0xFF4DFFC3)
    override val SOSO = Color(0xFF66FF66)
    override val BAD = Color(0xFFFFD24D)
    override val SO_BAD = Color(0xFFFFA64D)
    override val TOO_BAD = Color(0xFFFF794D)
    override val WORST = Color(0xFF262626)

    override val BLACK = Color(0xFF000000)
    override val WHITE = Color(0xFFFFFFFF)

    override var BACKGROUND = Color(0xFF171717)
}