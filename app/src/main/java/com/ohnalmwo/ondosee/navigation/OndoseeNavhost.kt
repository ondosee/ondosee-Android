package com.ohnalmwo.ondosee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.main.navigation.mainScreen
import com.ohnalmwo.model.enum.Route
import com.ohnalmwo.setting.navigation.navigateToSettingAlarm
import com.ohnalmwo.setting.navigation.navigateToSettingFont
import com.ohnalmwo.setting.navigation.navigateToSettingTheme
import com.ohnalmwo.setting.navigation.settingAlarmScreen
import com.ohnalmwo.setting.navigation.settingFontScreen
import com.ohnalmwo.setting.navigation.settingScreen
import com.ohnalmwo.setting.navigation.settingThemeScreen
import dev.chrisbanes.haze.HazeState

@Composable
fun OndoseeNavHost(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main,
        modifier = modifier
    ) {
        mainScreen(
            hazeState = hazeState,
        )

        settingScreen(
            hazeState = hazeState,
            onThemeClick = navController::navigateToSettingTheme,
            onFontClick = navController::navigateToSettingFont,
            onAlarmClick = navController::navigateToSettingAlarm
        )

        settingThemeScreen(
            hazeState = hazeState,
            onBackClick = navController::popBackStack
        )

        settingFontScreen(
            hazeState = hazeState,
            onBackClick = navController::popBackStack
        )

        settingAlarmScreen(
            hazeState = hazeState,
            onBackClick = navController::popBackStack
        )
        composable<Route.Weekly> {

        }
    }
}