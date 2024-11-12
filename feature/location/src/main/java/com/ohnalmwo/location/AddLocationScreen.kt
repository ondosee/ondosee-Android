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
import com.ohnalmwo.design_system.component.textfield.SearchTextField
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.LocationList
import com.ohnalmwo.location.component.LocationText
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.location.viewmodel.LocationViewModel
import com.ohnalmwo.ui.rememberFlowWithLifecycle

@Composable
fun AddLocationRoute(
    navigateToBack: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    val search by viewModel.search.collectAsStateWithLifecycle()

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is LocationEffect.NavigateToBack -> navigateToBack()
                else -> Unit
            }
        }
    }

    AddLocationScreen(
        state = state,
        search = search,
        onSearchChange = viewModel::onSearchChange,
        navigateToBack = { viewModel.sendEffect(LocationEffect.NavigateToBack) }
    )
}

@Composable
fun AddLocationScreen(
    state: LocationState,
    search: String,
    onSearchChange: (String) -> Unit,
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
                setText = search,
                singleLine = true,
                onValueChange = onSearchChange
            )
            LocationList(
                searchQuery = search,
                locations = state.location.locations
            ) {}
        }
    }
}