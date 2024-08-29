package com.ohnalmwo.ondosee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.model.enum.Route

@Composable
fun OndoseeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main,
        modifier = modifier
    ) {
        composable<Route.Main>{

        }
        composable<Route.Weekly>{

        }
        composable<Route.Setting>{

        }
    }
}