package com.minstone.weekly_weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.model.enum.BackgroundType
import com.ohnalmwo.ui.getBackgroundColors

@Composable
fun Temperature(
    modifier: Modifier,
    low: Int,
    high: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "${low}°",
            style = typography.textMedium,
            color = colors.THEME_WHITE.copy(alpha = 0.5f),
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(colors.WHITE.copy(alpha = 0.5f), colors.WHITE)
                    )
                )
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, end = 16.dp),
            text = "${high}°",
            style = typography.textMedium,
            color = colors.THEME_WHITE,
            fontWeight = FontWeight.Bold
        )
    }
}