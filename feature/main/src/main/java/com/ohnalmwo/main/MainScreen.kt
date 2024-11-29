package com.ohnalmwo.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.main.component.LoadingComponent
import com.ohnalmwo.main.component.MainComponent
import com.ohnalmwo.main.component.TutorialDialog
import com.ohnalmwo.main.viewmodel.MainScreenReducer.*
import com.ohnalmwo.main.viewmodel.MainViewModel
import com.ohnalmwo.ui.rememberFlowWithLifecycle
import dev.chrisbanes.haze.HazeState

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
        viewModel.getSavedLocations()
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

    if (state.isLoading) {
        LoadingComponent(hazeState = hazeState)
    } else {
        MainComponent(
            hazeState = hazeState,
            state = state,
            weathers = weathers,
            motionScene = motionScene,
            navigateToLocation = navigateToLocation
        )
    }

    if (openDialog) {
        TutorialDialog(
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