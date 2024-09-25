package com.ohnalmwo.location.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun LocationCountText(
    modifier: Modifier = Modifier,
    size: Int
) {
    Text(
        text = "$size/5",
        style = typography.textLarge,
        fontWeight = FontWeight.Bold,
        color = colors.TERTIARY,
        modifier = modifier
    )
}