package com.minstone.weekly_weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.ui.appendCelsiusSymbol


@Composable
fun TodayTemperature(
    modifier: Modifier,
    nowTemperature: Int,
    minTemperature: Int,
    maxTemperature: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier,
            text = nowTemperature.toString().appendCelsiusSymbol(),
            style = typography.titleLarge,
            color = colors.THEME_WHITE,
            fontWeight = FontWeight.Bold
        )

        Row {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = "최저: ${minTemperature.toString().appendCelsiusSymbol()}",
                style = typography.textMedium,
                color = colors.THEME_WHITE.copy(alpha = 0.75f),
                fontWeight = FontWeight.Normal
            )

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "최고: ${maxTemperature.toString().appendCelsiusSymbol()}",
                style = typography.textMedium,
                color = colors.THEME_WHITE.copy(alpha = 0.75f),
                fontWeight = FontWeight.Normal
            )
        }
    }
}
