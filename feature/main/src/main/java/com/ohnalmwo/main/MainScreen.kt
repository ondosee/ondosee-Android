package com.ohnalmwo.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.LocationServices
import com.ohnalmwo.main.component.LoadingComponent
import com.ohnalmwo.main.component.MainComponent
import com.ohnalmwo.main.component.TutorialDialog
import com.ohnalmwo.main.viewmodel.MainScreenReducer.*
import com.ohnalmwo.main.viewmodel.MainViewModel
import com.ohnalmwo.ui.rememberFlowWithLifecycle
import dev.chrisbanes.haze.HazeState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainRoute(
    hazeState: HazeState,
    navigateToLocation: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)
    val findLocationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = it.latitude to it.longitude
                    viewModel.getWeatherSignificant(x = it.longitude, y = it.latitude)
                }
            }
        } else {
            findLocationPermissionState.launchPermissionRequest()
        }
        viewModel.getSavedLocations()
        viewModel.getTutorialDialogState()
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is MainEffect.NavigateToLocation -> navigateToLocation()
            }
        }
    }

    MainScreen(
        context = context,
        hazeState = hazeState,
        state = state,
        onDismissClick = viewModel::setTutorialDialogState,
        navigateToLocation = { viewModel.sendEffect(MainEffect.NavigateToLocation) }
    )
}

@Composable
fun MainScreen(
    context: Context,
    hazeState: HazeState,
    state: MainState,
    onDismissClick: (Boolean) -> Unit,
    navigateToLocation: () -> Unit
) {
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
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

    if (state.openDialog) {
        TutorialDialog(
            openDialog = state.openDialog,
            onStateChange = onDismissClick,
            onDismissClick = { onDismissClick(false) },
            onCheckClick = { onDismissClick(false) }
        )
    }
}

@PreviewLightDark
@Composable
fun MainScreenPreview() {
    //MainScreen()
}