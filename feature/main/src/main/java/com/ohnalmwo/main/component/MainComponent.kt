package com.ohnalmwo.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.ohnalmwo.design_system.component.lottie.AnimatedLottie
import com.ohnalmwo.design_system.component.topbar.OndoseeTopBar
import com.ohnalmwo.design_system.icons.MenuIcon
import com.ohnalmwo.model.WeatherDetail
import com.ohnalmwo.ui.getAnimationLottie
import com.ohnalmwo.ui.getBackgroundColors
import com.ohnalmwo.ui.getForecastType
import com.ohnalmwo.ui.getWeatherConditionDescriptionText
import com.ohnalmwo.ui.getWeatherConditionText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    weathers: List<WeatherDetail>,
    motionScene: String,
    navigateToLocation: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = weathers[0].significant.getBackgroundColors()))
            .haze(state = hazeState)
            .statusBarsPadding()
    ) {
        OndoseeTopBar(content = { MenuIcon() }) {
            navigateToLocation()
        }
        MotionLayout(
            modifier = Modifier.fillMaxWidth(),
            motionScene = MotionScene(content = motionScene),
        ) {
            SignificantWeatherText(
                modifier = Modifier.layoutId("significantWeatherText"),
                weathers = weathers,
                backgroundType = weathers[0].significant
            )
            AnimatedLottie(
                modifier = Modifier.layoutId("animatedLottie"),
                rawId = weathers[0].significant.getAnimationLottie()
            )
            WeatherConditionText(
                modifier = Modifier.layoutId("weatherConditionText"),
                text = weathers[0].significant.getWeatherConditionText()
            )
            WeatherConditionDescriptionText(
                modifier = Modifier.layoutId("weatherConditionDescriptionText"),
                text = weathers[0].significant.getWeatherConditionDescriptionText()
            )
            LazyColumn(
                modifier = Modifier.layoutId("weatherForecastCard"),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = weathers,
                    key = { it.significant }
                ) {
                    WeatherForecastCard(
                        weather = it,
                        type = it.significant.getForecastType()
                    )
                }
            }
        }
    }
}