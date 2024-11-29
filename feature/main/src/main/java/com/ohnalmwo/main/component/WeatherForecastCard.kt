package com.ohnalmwo.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.BadIcon
import com.ohnalmwo.design_system.icons.CloudIcon
import com.ohnalmwo.design_system.icons.GoodIcon
import com.ohnalmwo.design_system.icons.RainIcon
import com.ohnalmwo.design_system.icons.SoBadIcon
import com.ohnalmwo.design_system.icons.SoGoodIcon
import com.ohnalmwo.design_system.icons.TooBadIcon
import com.ohnalmwo.design_system.icons.VeryGoodIcon
import com.ohnalmwo.design_system.icons.WorstIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.main.util.createDescriptionText
import com.ohnalmwo.main.util.isShowCard
import com.ohnalmwo.model.WeatherDetail
import com.ohnalmwo.model.enum.ForecastType
import com.ohnalmwo.ui.appendMicrograms
import com.ohnalmwo.ui.appendMicrogramsPerCubicMeter
import com.ohnalmwo.ui.appendPercent
import com.ohnalmwo.ui.toKoreanHourFormat
import kotlinx.datetime.LocalTime

@Composable
fun WeatherForecastCard(
    modifier: Modifier = Modifier,
    weather: WeatherDetail,
    type: ForecastType
) {
    if (type != ForecastType.TEMPERATURE && type.isShowCard(weather)) {
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
                color = colors.WHITE.copy(.75f)
            )
            Text(
                text = type.createDescriptionText(weather),
                style = typography.textMedium,
                fontWeight = FontWeight.Medium,
                color = colors.WHITE
            )
            LazyRow(
                modifier = Modifier.padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = weather.timeZone,
                    key = { it.time.hour }
                ) { item ->
                    WeatherForecastCardItem(
                        modifier = Modifier.width(40.dp),
                        time = item.time,
                        type = type,
                        content = item.value
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherForecastCardItem(
    modifier: Modifier = Modifier,
    time: LocalTime,
    type: ForecastType,
    content: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = time.toKoreanHourFormat(),
            style = typography.caption,
            fontWeight = FontWeight.Bold,
            color = colors.WHITE.copy(.75f)
        )
        when (type) {
            ForecastType.RAIN -> {
                if (content.toInt() >= 30) RainIcon()
                else CloudIcon()
                Text(
                    text = content.appendPercent(),
                    style = typography.caption,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE.copy(.75f)
                )
            }
            ForecastType.FINE_DUST -> {
                getDustIcon(value = content.toInt())
                Text(
                    text = content.appendMicrograms(),
                    style = typography.caption,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE.copy(.75f)
                )
            }
            ForecastType.ULTRAFINE_DUST -> {
                getUltraDustIcon(value = content.toInt())
                Text(
                    text = content.appendMicrogramsPerCubicMeter(),
                    style = typography.caption,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE.copy(.75f)
                )
            }
            else -> Unit
        }
    }
}

@Composable
fun getDustIcon(value: Int) = when (value) {
    in 0..15 -> { VeryGoodIcon() }
    in 16..30 -> { SoGoodIcon() }
    in 31..40 -> { GoodIcon() }
    in 41..50 -> { BadIcon() }
    in 51..75 -> { SoBadIcon() }
    in 76..100 -> { TooBadIcon() }
    else -> { WorstIcon() }
}

@Composable
fun getUltraDustIcon(value: Int) = when (value) {
    in 0..7 -> { VeryGoodIcon() }
    in 8..15 -> { SoGoodIcon() }
    in 16..20 -> { GoodIcon() }
    in 21..25 -> { BadIcon() }
    in 26..38 -> { SoBadIcon() }
    in 39..50 -> { TooBadIcon() }
    else -> { WorstIcon() }
}