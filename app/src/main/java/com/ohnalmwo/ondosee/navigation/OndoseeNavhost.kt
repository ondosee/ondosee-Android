package com.ohnalmwo.ondosee.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.Route
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun OndoseeNavHost(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main,
        modifier = modifier.haze(state = hazeState)
    ) {
        composable<Route.Main> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(600.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = colors.BACKGROUND_RAIN
                        )
                    )
                    .statusBarsPadding()
                ) {
                LazyColumn {
                    items(10) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(colors.BLACK)
                        )
                        Spacer(modifier = Modifier.size(50.dp))
                    }
                }
            }
        }
        composable<Route.Weekly> {

        }
        composable<Route.Setting> {

        }
    }
}