package com.ohnalmwo.ondosee.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ohnalmwo.design_system.component.bottombar.BottomNavigationBar
import com.ohnalmwo.design_system.theme.OndoseeTheme
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.ThemeType
import com.ohnalmwo.ondosee.navigation.OndoseeNavHost

@Composable
fun OndoseeApp() {

    val navController = rememberNavController()

    OndoseeTheme(themeMode = ThemeType.SYSTEM) {
        CompositionLocalProvider {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                }
            ) { innerPadding ->
                OndoseeNavHost(navController = navController)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(600.dp)
                        .padding(innerPadding)
                        .background(
                            brush = Brush.linearGradient(
                                colors = colors.BACKGROUND_RAIN
                            )
                        )
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
        }
    }
}