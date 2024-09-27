package com.ohnalmwo.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.location.AddLocationScreen
import com.ohnalmwo.location.LocationManagementScreen
import com.ohnalmwo.location.LocationScreen
import com.ohnalmwo.model.enum.Route

fun NavController.navigateToLocation(navOptions: NavOptions? = null) {
    this.navigate(Route.Location, navOptions)
}

fun NavGraphBuilder.locationScreen(
    navigateToLocationManagement: () -> Unit,
    navigateToAddLocation: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Route.Location> {
        LocationScreen(
            navigateToLocationManagement = navigateToLocationManagement,
            navigateToAddLocation = navigateToAddLocation,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToLocationManagement(navOptions: NavOptions? = null) {
    this.navigate(Route.LocationManagement, navOptions)
}

fun NavGraphBuilder.locationManagementScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.LocationManagement> {
        LocationManagementScreen(navigateToBack = navigateToBack)
    }
}

fun NavController.navigateToAddLocation(navOptions: NavOptions? = null) {
    this.navigate(Route.AddLocation, navOptions)
}

fun NavGraphBuilder.addLocationScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.AddLocation> {
        AddLocationScreen(navigateToBack = navigateToBack)
    }
}