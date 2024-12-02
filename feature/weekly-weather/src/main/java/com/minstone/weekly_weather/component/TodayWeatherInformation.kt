package com.minstone.weekly_weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.CloudIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.ui.appendMicrograms
import com.ohnalmwo.ui.appendPercent

@Composable
fun TodayWeatherInformation(
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "오늘",
                style = typography.titleSmall,
                fontWeight = FontWeight.Medium,
                color = colors.WHITE.copy(.75f)
            )
            Row {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "구름",
                    style = typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE.copy(.75f)
                )

                CloudIcon()
            }
        }

        TodayTemperature(
            modifier = Modifier.padding(start = 16.dp),
            nowTemperature = 23,
            minTemperature = 20,
            maxTemperature = 25
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                significantText(
                    modifier = Modifier.padding(end = 24.dp),
                    title = "강수 확률",
                    content = "90".appendPercent()
                )

                significantText(
                    modifier = Modifier,
                    title = "미세먼지 농도",
                    content = "200".appendMicrograms()
                )
            }

            significantText(
                modifier = Modifier,
                title = "오늘의 특이사항",
                content = "비,미세먼지"
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .background(color = colors.WHITE)
        )
    }
}

@Composable
private fun significantText(
    modifier: Modifier,
    title: String,
    content: String
) {
    Column {
        Text(
            modifier = modifier.padding(bottom = 4.dp),
            text = title,
            style = typography.caption,
            fontWeight = FontWeight.Medium,
            color = colors.WHITE.copy(.75f)
        )

        Text(
            text = content,
            style = typography.textMedium,
            fontWeight = FontWeight.Bold,
            color = colors.WHITE
        )
    }
}