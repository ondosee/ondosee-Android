package com.ohnalmwo.design_system.theme.color

import androidx.compose.ui.graphics.Color
import com.goms.design_system.theme.ColorTheme

object LightColor : ColorTheme() {
    override val PRIMARY = Color(0xFF00BFFF)
    override val WARNING = Color(0xFFFF3333)
    override val ABLE = Color(0xFF26D926)
    override val DISABLE = Color(0xFFCCCCCC)
    override val SECONDARY = Color(0xBFFFFFFF)
    override val TERTIARY = Color(0x80FFFFFF)

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

    override var BACKGROUND = Color(0xFFF9FAFA)

    override var MAIN_BACKGROUND: List<Color>
        get() = MainBackgroundColor.mainBackground
        set(value) {
            MainBackgroundColor.mainBackground = value
        }
}