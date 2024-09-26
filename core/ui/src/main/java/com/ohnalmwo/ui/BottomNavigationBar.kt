package com.ohnalmwo.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.model.enum.Route
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    navController: NavController,
) {
    val bottomBarItems = listOf(
        BottomBarItem.Main,
        BottomBarItem.Weekly,
        BottomBarItem.Setting
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val route = navController.currentDestination?.route?.removePrefix("com.ohnalmwo.model.enum.Route.")
    val isSetting = route != Route.Setting.toString()

    @Composable
    fun isSettingColor(alpha: Float): Color {
        return if(isSetting) colors.WHITE.copy(alpha = alpha) else colors.THEME_BLACK.copy(alpha = alpha)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                style = HazeStyle(
                    tint = isSettingColor(.25f),
                    blurRadius = 10.dp
                )
            )
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        isSettingColor(.8f),
                        isSettingColor(.25f),
                    ),
                ),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
            .windowInsetsPadding(
                WindowInsets
                    .safeDrawing
                    .only(WindowInsetsSides.Bottom)
            )
    ) {
        bottomBarItems.forEach { bottomBarItem ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(bottomBarItem.route::class)
            } == true

            CompositionLocalProvider(
                LocalContentColor provides if (isSelected) colors.WHITE else colors.WHITE.copy(.5f)
            ) {
                val alpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .35f,
                    label = "Alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "Scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                navController.navigate(bottomBarItem.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = bottomBarItem.icon),
                        colorFilter = ColorFilter.tint(color = isSettingColor(1f)),
                        contentDescription = "Navigation Icon"
                    )
                    Text(
                        text = bottomBarItem.title,
                        style = typography.textSmall,
                        fontWeight = FontWeight.Medium,
                        color = isSettingColor(1f)
                    )
                }
            }
        }
    }
}