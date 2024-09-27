package com.ohnalmwo.main.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun WeatherConditionDescriptionText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = typography.textLarge,
        fontWeight = FontWeight.Medium,
        color = colors.WHITE.copy(.75f),
        modifier = modifier
    )
}