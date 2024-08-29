package com.ohnalmwo.ondosee.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.ohnalmwo.design_system.component.bottombar.BottomNavigationBar
import com.ohnalmwo.design_system.theme.OndoseeTheme
import com.ohnalmwo.model.enum.ThemeType
import com.ohnalmwo.ondosee.navigation.OndoseeNavHost
import dev.chrisbanes.haze.HazeState

@Composable
fun OndoseeApp() {

    val navController = rememberNavController()
    val hazeState = remember { HazeState() }

    OndoseeTheme(themeMode = ThemeType.SYSTEM) {
        CompositionLocalProvider {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        hazeState = hazeState,
                        navController = navController
                    )
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