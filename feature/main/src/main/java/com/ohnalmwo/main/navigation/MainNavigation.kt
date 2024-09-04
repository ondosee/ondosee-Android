package com.ohnalmwo.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ohnalmwo.main.MainScreen
import com.ohnalmwo.model.enum.Route
import dev.chrisbanes.haze.HazeState

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(Route.Main, navOptions)
}

fun NavGraphBuilder.loginScreen(
    hazeState: HazeState
) {
    composable<Route.Main> {
        MainScreen(
            hazeState = hazeState
        )
    }
}