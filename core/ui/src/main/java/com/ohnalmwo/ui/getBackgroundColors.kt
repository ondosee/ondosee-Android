package com.ohnalmwo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.BackgroundType

@Composable
fun getBackgroundColors(type: BackgroundType): List<Color> {
    return when (type) {
        BackgroundType.RAIN -> colors.BACKGROUND_RAIN
        BackgroundType.SNOW -> colors.BACKGROUND_SNOW
        BackgroundType.THUNDER -> colors.BACKGROUND_THUNDER
        BackgroundType.WORST -> colors.BACKGROUND_WORST
        BackgroundType.MISE_TOO_BAD -> colors.BACKGROUND_MISE_TOO_BAD
        BackgroundType.MISE_SO_BAD -> colors.BACKGROUND_MISE_SO_BAD
        BackgroundType.MISE_BAD -> colors.BACKGROUND_MISE_BAD
        BackgroundType.HEAT_WAVE -> colors.BACKGROUND_HEAT_WAVE
        BackgroundType.HEAVE_SNOW -> colors.BACKGROUND_HEAVE_SNOW
    }
}