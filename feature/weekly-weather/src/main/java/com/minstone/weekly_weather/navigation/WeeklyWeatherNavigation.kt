package com.minstone.weekly_weather.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.minstone.weekly_weather.WeeklyWeatherScreen
import com.ohnalmwo.model.enum.Route
import dev.chrisbanes.haze.HazeState

fun NavController.navigateToWeeklyWeather(navOptions: NavOptions? = null) {
    this.navigate(Route.Weekly, navOptions)
}

fun NavGraphBuilder.weeklyWeatherScreen(
    hazeState: HazeState,
    navigateToLocation: () -> Unit
) {
    composable<Route.Weekly> {
        WeeklyWeatherScreen(
            hazeState = hazeState,
            navigateToLocation = navigateToLocation
        )
    }
}