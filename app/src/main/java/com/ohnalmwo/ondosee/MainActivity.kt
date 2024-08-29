package com.ohnalmwo.ondosee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ohnalmwo.ondosee.ui.OndoseeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            DisposableEffect(systemUiController, useDarkIcons) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons,
                    isNavigationBarContrastEnforced = false
                )

                onDispose {}
            }

            OndoseeApp()
        }
    }
}