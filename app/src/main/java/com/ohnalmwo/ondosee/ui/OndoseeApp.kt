package com.ohnalmwo.ondosee.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.ohnalmwo.design_system.theme.OndoseeTheme
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.ThemeType

@Composable
fun OndoseeApp() {
    OndoseeTheme(themeMode = ThemeType.SYSTEM.value) {
        CompositionLocalProvider {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = colors.BACKGROUND_RAIN
                        )
                    )
                    .fillMaxSize()
            )
        }
    }
}