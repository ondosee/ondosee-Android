package com.ohnalmwo.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.goms.design_system.theme.ColorTheme
import com.ohnalmwo.design_system.theme.color.DarkColor
import com.ohnalmwo.design_system.theme.color.LightColor
import com.ohnalmwo.model.enum.ThemeType

internal val LocalColorTheme = compositionLocalOf<ColorTheme> { error("No ColorTheme provided") }
internal val LocalTypography = compositionLocalOf<OndoseeTypography> { error("No OndoseeTypography provided") }

@Composable
fun OndoseeTheme(
    themeMode: ThemeType = ThemeType.SYSTEM,
    content: @Composable () -> Unit
) {
    val theme = when (themeMode) {
        ThemeType.SYSTEM -> isSystemInDarkTheme()
        ThemeType.DARK -> true
        ThemeType.LIGHT -> false
    }

    val colors = if (theme) DarkColor else LightColor
    val typography = OndoseeTypography

    CompositionLocalProvider(
        LocalColorTheme provides colors,
        LocalTypography provides typography
    ) {
        content()
    }
}

object OndoseeTheme {
    val colors: ColorTheme
        @Composable
        get() = LocalColorTheme.current
    val typography: OndoseeTypography
        @Composable
        get() = LocalTypography.current
}

