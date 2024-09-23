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

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting, navOptions)
}

fun NavGraphBuilder.settingScreen(
    onThemeClick: () -> Unit,
    onFontClick: () -> Unit,
    onAlarmClick: () -> Unit
) {
    composable<Route.Setting> {
        SettingScreen(
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
    onBackClick: () -> Unit
) {
    composable<Route.SettingTheme> {
        SettingThemeScreen(
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToSettingFont(navOptions: NavOptions? = null) {
    this.navigate(Route.SettingFont, navOptions)
}

fun NavGraphBuilder.settingFontScreen(
    onBackClick: () -> Unit
) {
    composable<Route.SettingFont> {
        SettingFontScreen(
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToSettingAlarm(navOptions: NavOptions? = null) {
    this.navigate(Route.SettingAlarm, navOptions)
}

fun NavGraphBuilder.settingAlarmScreen(
    onBackClick: () -> Unit
) {
    composable<Route.SettingAlarm> {
        SettingAlarmScreen(
            onBackClick = onBackClick
        )
    }
}