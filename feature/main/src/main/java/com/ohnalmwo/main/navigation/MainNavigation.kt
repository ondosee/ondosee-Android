package com.ohnalmwo.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.main.MainScreen
import com.ohnalmwo.model.enum.Route
import dev.chrisbanes.haze.HazeState

fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(Route.Main, navOptions)
}

fun NavGraphBuilder.mainScreen(
    hazeState: HazeState,
) {
    composable<Route.Main> {
        MainScreen(
            hazeState = hazeState,
        )
    }
}