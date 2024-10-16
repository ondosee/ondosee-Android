package com.ohnalmwo.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.ohnalmwo.model.enum.BackgroundType
import com.ohnalmwo.ui.getBackgroundColors
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
        viewModel.getWeatherSignificant(x = 5.1, y = 4.2)
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is MainEffect.NavigateToLocation -> navigateToLocation()
            }
        }
    }

    if (state.isLoading) {

    } else {
        MainScreen(
            hazeState = hazeState,
            navigateToLocation = { viewModel.sendEffect(MainEffect.NavigateToLocation) }
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MainScreen(
    hazeState: HazeState,
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = getBackgroundColors(type = BackgroundType.RAIN)
                )
            )
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
                text = "90%",
                backgroundType = BackgroundType.RAIN
            )
            AnimatedLottie(
                modifier = Modifier.layoutId("animatedLottie"),
                rawId = com.ohnalmwo.design_system.R.raw.rainy
            )
            WeatherConditionText(
                modifier = Modifier.layoutId("weatherConditionText"),
                text = "비가 올 것 같아요!"
            )
            WeatherConditionDescriptionText(
                modifier = Modifier.layoutId("weatherConditionDescriptionText"),
                text = "우산을 미리 챙겨주세요!"
            )
            WeatherForecastCard(
                modifier = Modifier.layoutId("weatherForecastCardFirst"),
                description = "오전 3시부터 5시간 동안 비가 올 예정이에요.\n" +
                        "우산을 가져가세요."
            )
            WeatherForecastCard(
                modifier = Modifier.layoutId("weatherForecastCardSecond"),
                description = "오후 5시부터 9시간 동안 미세먼지/꽂가루가 심해요.\n" +
                        "마스크를 가져가세요."
            )
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