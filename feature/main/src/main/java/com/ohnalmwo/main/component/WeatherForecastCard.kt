package com.ohnalmwo.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun WeatherForecastCard(
    modifier: Modifier = Modifier,
    description: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = colors.WHITE.copy(alpha = .2f))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "시간 별 일기예보",
            style = typography.textSmall,
            fontWeight = FontWeight.Bold,
            color = colors.SECONDARY
        )
        Text(
            text = description,
            style = typography.textMedium,
            fontWeight = FontWeight.Medium,
            color = colors.WHITE
        )
        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                WeatherForecastCardItem(
                    time = "오전 12시",
                    content = "40%"
                )
            }
        }
    }
}

@Composable
fun WeatherForecastCardItem(
    time: String,
    content: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = time,
            style = typography.caption,
            fontWeight = FontWeight.Bold,
            color = colors.SECONDARY
        )
        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")
        Text(
            text = content,
            style = typography.caption,
            fontWeight = FontWeight.Medium,
            color = colors.SECONDARY
        )
    }
}