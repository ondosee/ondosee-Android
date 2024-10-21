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
    navigateToSettingTheme: () -> Unit,
    navigateToSettingFont: () -> Unit,
    navigateToSettingAlarm: () -> Unit
) {
    composable<Route.Setting> {
        SettingScreen(
            hazeState = hazeState,
            navigateToSettingTheme = navigateToSettingTheme,
            navigateToSettingFont = navigateToSettingFont,
            navigateToSettingAlarm = navigateToSettingAlarm
        )
    }
}

fun NavController.navigateToSettingTheme(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting.SettingTheme, navOptions)
}

fun NavGraphBuilder.settingThemeScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.Setting.SettingTheme> {
        SettingThemeScreen(
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToSettingFont(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting.SettingFont, navOptions)
}

fun NavGraphBuilder.settingFontScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.Setting.SettingFont> {
        SettingFontScreen(
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToSettingAlarm(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting.SettingAlarm, navOptions)
}

fun NavGraphBuilder.settingAlarmScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.Setting.SettingAlarm> {
        SettingAlarmScreen(
            navigateToBack = navigateToBack
        )
    }
}