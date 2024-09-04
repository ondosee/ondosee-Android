package com.ohnalmwo.ondosee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.main.navigation.loginScreen
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
        loginScreen(hazeState = hazeState)
        composable<Route.Weekly> {

        }
        composable<Route.Setting> {

        }
    }
}