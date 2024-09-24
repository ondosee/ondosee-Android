package com.ohnalmwo.ondosee.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ohnalmwo.ui.BottomNavigationBar
import com.ohnalmwo.design_system.theme.OndoseeTheme
import com.ohnalmwo.model.enum.Route
import com.ohnalmwo.model.enum.ThemeType
import com.ohnalmwo.ondosee.navigation.OndoseeNavHost
import dev.chrisbanes.haze.HazeState

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OndoseeApp() {

    val navController = rememberNavController()
    val hazeState = remember { HazeState() }

    OndoseeTheme(themeMode = ThemeType.SYSTEM) {
        CompositionLocalProvider {
            val currentRoute by navController.currentBackStackEntryAsState()
            val showBottomBar = remember(currentRoute) {
                currentRoute?.destination?.route in setOf(
                    Route.Main.toString(),
                    Route.Weekly.toString(),
                    Route.Setting.toString()
                )
            }

            Scaffold(
                bottomBar = {
                    if (showBottomBar) {
                        BottomNavigationBar(
                            hazeState = hazeState,
                            navController = navController
                        )
                    }
                }
            ) { _ ->
                OndoseeNavHost(
                    hazeState = hazeState,
                    navController = navController
                )
            }
        }
    }
}