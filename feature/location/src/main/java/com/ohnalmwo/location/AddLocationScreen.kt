package com.ohnalmwo.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.component.dialog.OndoseeDialog
import com.ohnalmwo.design_system.component.textfield.SearchTextField
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.LocationList
import com.ohnalmwo.location.component.LocationText
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.location.viewmodel.LocationViewModel
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.ui.rememberFlowWithLifecycle
import kotlinx.coroutines.delay

@Composable
fun AddLocationRoute(
    navigateToLocationManagement: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(Unit) {
        viewModel.getSavedLocations()
    }

    LaunchedEffect(state.search) {
        delay(300L)
        if (state.search.isNotBlank()) viewModel.getLocationCoordinate(search = state.search)
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is LocationEffect.NavigateToLocationManagement -> navigateToLocationManagement()
                is LocationEffect.NavigateToBack -> navigateToBack()
                else -> Unit
            }
        }
    }

    AddLocationScreen(
        state = state,
        onSearchValueChange = { viewModel.sendEvent(LocationEvent.OnSearchValueChange(it)) },
        onListItemClick = viewModel::setSavedLocations,
        setDialog = { viewModel.sendEvent(LocationEvent.SetLogoutDialog(it)) },
        navigateToLocationManagement = { viewModel.sendEffect(LocationEffect.NavigateToLocationManagement) },
        navigateToBack = { viewModel.sendEffect(LocationEffect.NavigateToBack) }
    )
}

@Composable
fun AddLocationScreen(
    state: LocationState,
    onSearchValueChange: (String) -> Unit,
    onListItemClick: (LocationInfo) -> Unit,
    setDialog: (Boolean) -> Unit,
    navigateToLocationManagement: () -> Unit,
    navigateToBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OndoseeBackButton {
            navigateToBack()
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            LocationText(text = "위치 추가하기")
            SearchTextField(
                modifier = Modifier.padding(top = 16.dp),
                placeHolder = "도시 또는 공항 검색",
                setText = state.search,
                singleLine = true,
                onValueChange = onSearchValueChange
            )
            LocationList(
                searchQuery = state.search,
                locations = state.locations.locations,
                onClick = {
                    if (state.localLocations.size >= 5) setDialog(true)
                    else onListItemClick(it)
                }
            )
        }
    }

    if (state.openDialog) {
        OndoseeDialog(
            openDialog = state.openDialog,
            title = "위치 목록이 가득 찼습니다.",
            content = "다른 위치를 삭제하시겠어요?",
            firstText = "위치 목록으로",
            secondText = "취소",
            onFirstClick = {
                setDialog(false)
                navigateToLocationManagement()
            },
            onSecondClick = {
                setDialog(false)
            }
        )
    }
}