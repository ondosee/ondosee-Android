package com.ohnalmwo.ondosee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.location.navigation.addLocationScreen
import com.ohnalmwo.location.navigation.locationManagementScreen
import com.ohnalmwo.location.navigation.locationScreen
import com.ohnalmwo.location.navigation.navigateToAddLocation
import com.ohnalmwo.location.navigation.navigateToLocation
import com.ohnalmwo.location.navigation.navigateToLocationManagement
import com.ohnalmwo.main.navigation.mainScreen
import com.ohnalmwo.model.enum.Route
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
            navigateToAddLocation = navController::navigateToAddLocation
        )
        locationManagementScreen()
        addLocationScreen()
        composable<Route.Weekly> {

        }
        composable<Route.Setting> {

        }
    }
}