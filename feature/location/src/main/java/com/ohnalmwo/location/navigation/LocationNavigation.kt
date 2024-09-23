package com.ohnalmwo.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.location.LocationScreen
import com.ohnalmwo.model.enum.Route

fun NavController.navigateToLocation(navOptions: NavOptions? = null) {
    this.navigate(Route.Location, navOptions)
}

fun NavGraphBuilder.locationScreen() {
    composable<Route.Location> {
        LocationScreen()
    }
}