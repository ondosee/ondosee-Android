package com.ohnalmwo.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.location.AddLocationRoute
import com.ohnalmwo.location.LocationManagementRoute
import com.ohnalmwo.location.LocationRoute
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
        LocationRoute(
            navigateToLocationManagement = navigateToLocationManagement,
            navigateToAddLocation = navigateToAddLocation,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToLocationManagement(navOptions: NavOptions? = null) {
    this.navigate(Route.Location.LocationManagement, navOptions)
}

fun NavGraphBuilder.locationManagementScreen(
    navigateToBack: () -> Unit
) {
    composable<Route.Location.LocationManagement> {
        LocationManagementRoute(navigateToBack = navigateToBack)
    }
}

fun NavController.navigateToAddLocation(navOptions: NavOptions? = null) {
    this.navigate(Route.Location.AddLocation, navOptions)
}

fun NavGraphBuilder.addLocationScreen(
    navigateToLocationManagement: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Route.Location.AddLocation> {
        AddLocationRoute(
            navigateToLocationManagement = navigateToLocationManagement,
            navigateToBack = navigateToBack
        )
    }
}