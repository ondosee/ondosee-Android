package com.ohnalmwo.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ohnalmwo.model.enum.Route
import com.ohnalmwo.setting.SettingScreen
import com.ohnalmwo.setting.SettingThemeScreen

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting, navOptions)
}

fun NavGraphBuilder.settingScreen(
    onThemeClick: () -> Unit
) {
    composable<Route.Setting> {
        SettingScreen(
            onThemeClick = onThemeClick
        )
    }
}