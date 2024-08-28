package com.ohnalmwo.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.goms.design_system.theme.ColorTheme
import com.ohnalmwo.design_system.theme.color.DarkColor
import com.ohnalmwo.design_system.theme.color.LightColor

enum class ThemeType(val value: String, val kr: String) {
    SYSTEM("System", "시스템 테마 설정"),
    DARK("Dark", "다크(기본)"),
    LIGHT("Light", "라이트")
}

internal val LocalColorTheme = compositionLocalOf<ColorTheme> { error("No ColorTheme provided") }
internal val LocalTypography = compositionLocalOf<OndoseeTypography> { error("No OndoseeTypography provided") }

@Composable
fun OndoseeTheme(
    themeMode: String = ThemeType.DARK.value,
    content: @Composable () -> Unit
) {
    val theme = when (themeMode) {
        ThemeType.SYSTEM.value -> isSystemInDarkTheme()
        ThemeType.DARK.value -> true
        ThemeType.LIGHT.value -> false
        else -> true
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

