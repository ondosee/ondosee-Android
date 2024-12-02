package com.minstone.weekly_weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.CloudIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun WeatherInformation(
    modifier: Modifier,
    date: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherInformationItem(
            modifier = Modifier.padding(start = 16.dp),
            date = date,
            content = "90%"
        )

        Temperature(
            modifier = Modifier.padding(start = 48.dp),
            minTemperature = 20,
            maxTemperature = 25
        )
    }

    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .fillMaxWidth()
            .background(color = colors.WHITE)
    )
}

@Composable
fun WeatherInformationItem(
    modifier: Modifier,
    date: String,
    content: String
) {
    Row(
        modifier = modifier.width(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date,
            style = typography.titleSmall,
            fontWeight = FontWeight.Medium,
            color = colors.WHITE.copy(.75f)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CloudIcon()

            Text(
                text = content,
                style = typography.caption,
                fontWeight = FontWeight.Medium,
                color = colors.WHITE.copy(.75f)
            )
        }
    }
}