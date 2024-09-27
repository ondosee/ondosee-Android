package com.ohnalmwo.ondosee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.location.navigation.*
import com.ohnalmwo.main.navigation.mainScreen
import com.ohnalmwo.model.enum.Route
import com.ohnalmwo.setting.navigation.*
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
            navigateToLocation = navController::navigateToLocation
        )
        locationScreen(
            navigateToLocationManagement = navController::navigateToLocationManagement,
            navigateToAddLocation = navController::navigateToAddLocation,
            navigateToBack = navController::popBackStack
        )
        locationManagementScreen(navigateToBack = navController::popBackStack)
        addLocationScreen(navigateToBack = navController::popBackStack)
        composable<Route.Weekly> {

        }
        settingScreen(
            hazeState = hazeState,
            navigateToSettingTheme = navController::navigateToSettingTheme,
            navigateToSettingFont = navController::navigateToSettingFont,
            navigateToSettingAlarm = navController::navigateToSettingAlarm
        )
        settingThemeScreen(
            navigateToBack = navController::popBackStack
        )
        settingFontScreen(
            navigateToBack = navController::popBackStack
        )
        settingAlarmScreen(
            navigateToBack = navController::popBackStack
        )
    }
}