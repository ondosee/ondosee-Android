package com.minstone.weekly_weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.minstone.weekly_weather.component.TodayWeatherInformation
import com.minstone.weekly_weather.component.WeatherInformation
import com.ohnalmwo.design_system.component.topbar.OndoseeTopBar
import com.ohnalmwo.design_system.icons.MenuIcon
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.enum.Significant
import com.ohnalmwo.ui.getBackgroundColors
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun WeeklyWeatherScreen(
    hazeState: HazeState,
    navigateToLocation: () -> Unit
) {
    val weatherDates = listOf("화", "수", "목", "금", "토", "일", "월")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = Significant.SNOW.getBackgroundColors()))
            .haze(state = hazeState)
            .statusBarsPadding()
    ) {
        OndoseeTopBar(
            list = listOf(LocationInfo("광주광역시 광산구", "127", "37")),
            content = { MenuIcon() }
        ) { navigateToLocation() }

        Spacer(modifier = Modifier.height(20.dp))

        TodayWeatherInformation(
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        weatherDates.forEach { date ->
            WeatherInformation(
                modifier = Modifier.padding(horizontal = 20.dp),
                date = date
            )
        }
    }
}