package com.ohnalmwo.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.design_system.component.bottomsheet.OptionBottomSheet
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.icons.HamburgerIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.LocationCard
import com.ohnalmwo.location.component.LocationCountText
import com.ohnalmwo.location.component.LocationText
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.location.viewmodel.LocationViewModel
import com.ohnalmwo.ui.rememberFlowWithLifecycle

@Composable
fun LocationRoute(
    navigateToLocationManagement: () -> Unit,
    navigateToAddLocation: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(Unit) {
        viewModel.getSavedLocations()
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is LocationEffect.NavigateToLocationManagement -> navigateToLocationManagement()
                is LocationEffect.NavigateToAddLocation -> navigateToAddLocation()
                is LocationEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    LocationScreen(
        state = state,
        navigateToLocationManagement = { viewModel.sendEffect(LocationEffect.NavigateToLocationManagement) },
        navigateToAddLocation = { viewModel.sendEffect(LocationEffect.NavigateToAddLocation) },
        navigateToBack = { viewModel.sendEffect(LocationEffect.NavigateToBack) }
    )
}

@Composable
fun LocationScreen(
    state: LocationState,
    navigateToLocationManagement: () -> Unit,
    navigateToAddLocation: () -> Unit,
    navigateToBack: () -> Unit
) {
    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OndoseeBackButton(
            content = {
                HamburgerIcon(tint = colors.PRIMARY)
            },
            onContentClick = {
                openBottomSheet = true
            }
        ) {
            navigateToBack()
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                LocationText(text = "위치")
                LocationCountText(size = state.localLocations.size)
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(
                    items = state.localLocations.distinct(),
                    key = { index, item -> item.title }
                ) { index, item ->
                    LocationCard(
                        location = item.title,
                        significant = "비 | 강수확률 90%",
                        isCurrentLocation = index == 0,
                        isExtension = true
                    )
                }
            }
        }

        if (openBottomSheet) {
            OptionBottomSheet(
                closeSheet = { openBottomSheet = false },
                navigateToLocationManagement = {
                    navigateToLocationManagement()
                },
                navigateToAddLocation = {
                    navigateToAddLocation()
                }
            )
        }
    }
}