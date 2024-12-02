package com.minstone.weekly_weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 16.dp)
    ) {
        Temperature(
            modifier = Modifier,
            minTemperature = minTemperature,
            maxTemperature = maxTemperature
        )

        NowTemperature(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-16).dp),
            nowTemperature = nowTemperature
        )
    }
}

@Composable
fun NowTemperature(
    modifier: Modifier,
    nowTemperature: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = nowTemperature.toString().appendCelsiusSymbol(),
            style = typography.textSmall,
            color = colors.THEME_WHITE,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .background(colors.BACKGROUND_SNOW[0]),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(colors.THEME_WHITE)
            )
        }
    }
}