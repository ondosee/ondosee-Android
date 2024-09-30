package com.ohnalmwo.location.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun LocationText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = colors.THEME_BLACK,
        modifier = modifier
    )
}