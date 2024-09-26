package com.ohnalmwo.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.model.enum.Route
import com.ohnalmwo.setting.SettingAlarmScreen
import com.ohnalmwo.setting.SettingFontScreen
import com.ohnalmwo.setting.SettingScreen
import com.ohnalmwo.setting.SettingThemeScreen
import dev.chrisbanes.haze.HazeState

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting, navOptions)
}

fun NavGraphBuilder.settingScreen(
    hazeState: HazeState,
    onThemeClick: () -> Unit,
    onFontClick: () -> Unit,
    onAlarmClick: () -> Unit
) {
    composable<Route.Setting> {
        SettingScreen(
            hazeState = hazeState,
            onThemeClick = onThemeClick,
            onFontClick = onFontClick,
            onAlarmClick = onAlarmClick
        )
    }
}

fun NavController.navigateToSettingTheme(navOptions: NavOptions? = null) {
    this.navigate(Route.SettingTheme, navOptions)
}

fun NavGraphBuilder.settingThemeScreen(
    hazeState: HazeState,
    onBackClick: () -> Unit
) {
    composable<Route.SettingTheme> {
        SettingThemeScreen(
            hazeState = hazeState,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToSettingFont(navOptions: NavOptions? = null) {
    this.navigate(Route.SettingFont, navOptions)
}

fun NavGraphBuilder.settingFontScreen(
    hazeState: HazeState,
    onBackClick: () -> Unit
) {
    composable<Route.SettingFont> {
        SettingFontScreen(
            hazeState = hazeState,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToSettingAlarm(navOptions: NavOptions? = null) {
    this.navigate(Route.SettingAlarm, navOptions)
}

fun NavGraphBuilder.settingAlarmScreen(
    hazeState: HazeState,
    onBackClick: () -> Unit
) {
    composable<Route.SettingAlarm> {
        SettingAlarmScreen(
            hazeState = hazeState,
            onBackClick = onBackClick
        )
    }
}