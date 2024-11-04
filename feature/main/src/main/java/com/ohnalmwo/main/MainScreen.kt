package com.ohnalmwo.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.design_system.component.lottie.AnimatedLottie
import com.ohnalmwo.design_system.component.topbar.OndoseeTopBar
import com.ohnalmwo.design_system.icons.MenuIcon
import com.ohnalmwo.main.component.SignificantWeatherText
import com.ohnalmwo.main.component.TutorialDialog
import com.ohnalmwo.main.component.WeatherConditionDescriptionText
import com.ohnalmwo.main.component.WeatherConditionText
import com.ohnalmwo.main.component.WeatherForecastCard
import com.ohnalmwo.main.viewmodel.MainScreenReducer.*
import com.ohnalmwo.main.viewmodel.MainViewModel
import com.ohnalmwo.ui.getAnimationLottie
import com.ohnalmwo.ui.getBackgroundColors
import com.ohnalmwo.ui.getForecastType
import com.ohnalmwo.ui.getWeatherConditionDescriptionText
import com.ohnalmwo.ui.getWeatherConditionText
import com.ohnalmwo.ui.rememberFlowWithLifecycle
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun MainRoute(
    hazeState: HazeState,
    navigateToLocation: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(Unit) {
        viewModel.getWeatherSignificant(x = 126.85250, y = 35.15944)
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is MainEffect.NavigateToLocation -> navigateToLocation()
            }
        }
    }

    MainScreen(
        hazeState = hazeState,
        state = state,
        navigateToLocation = { viewModel.sendEffect(MainEffect.NavigateToLocation) }
    )
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MainScreen(
    hazeState: HazeState,
    state: MainState,
    navigateToLocation: () -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    var openDialog by remember { mutableStateOf(true) }
    val weathers = state.significant.weathers

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = weathers[0].significant.getBackgroundColors()))
            .haze(state = hazeState)
            .statusBarsPadding()
    ) {
        OndoseeTopBar(content = { MenuIcon() }) {
           navigateToLocation()
        }
        if (state.isLoading) {

        } else {
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

    if (openDialog) {
        TutorialDialog(
            hazeState = hazeState,
            openDialog = openDialog,
            onStateChange = { openDialog = it },
            onDismissClick = { openDialog = false },
            onCheckClick = { openDialog = false }
        )
    }
}

@PreviewLightDark
@Composable
fun MainScreenPreview() {
    //MainScreen()
}