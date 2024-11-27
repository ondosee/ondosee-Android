package com.ohnalmwo.location

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.EditableLocationCard
import com.ohnalmwo.location.component.LocationCard
import com.ohnalmwo.location.component.LocationText
import com.ohnalmwo.location.util.dragModifier
import com.ohnalmwo.location.util.move
import com.ohnalmwo.location.util.rememberDragAndDropListState
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.location.viewmodel.LocationViewModel
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.ui.rememberFlowWithLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun LocationManagementRoute(
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
                is LocationEffect.NavigateToBack -> navigateToBack()
                else -> Unit
            }
        }
    }

    LocationManagementScreen(
        state = state,
        onUpdateAllLocations = viewModel::updateAllSavedLocations,
        onRemoveLocations = viewModel::removeSavedLocations,
        navigateToBack = { viewModel.sendEffect(LocationEffect.NavigateToBack) }
    )
}

@Composable
fun LocationManagementScreen(
    state: LocationState,
    onUpdateAllLocations: (List<LocationInfo>) -> Unit,
    onRemoveLocations: (Int) -> Unit,
    navigateToBack: () -> Unit
) {
    val newList = state.localLocations.toMutableList()
    val lazyListState = rememberLazyListState()
    val dragAndDropListState = rememberDragAndDropListState(lazyListState = lazyListState, key = state.localLocations) { from, to ->
        if (from != 0 && to != 0) {
            newList.move(from, to)
            onUpdateAllLocations(newList)
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var overscrollJob by remember { mutableStateOf<Job?>(null) }

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
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LocationText(text = "위치 관리하기")
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .pointerInput(Unit) {
                        detectDragGesturesAfterLongPress(
                            onDrag = { change, offset ->
                                change.consume()
                                dragAndDropListState.onDrag(offset)

                                if (overscrollJob?.isActive == true) return@detectDragGesturesAfterLongPress

                                dragAndDropListState
                                    .checkOverscroll()
                                    .takeIf { it != 0f }
                                    ?.let {
                                        overscrollJob = coroutineScope.launch {
                                            dragAndDropListState.lazyListState.scrollBy(it)
                                        }
                                    } ?: run { overscrollJob?.cancel() }
                            },
                            onDragStart = { offset ->
                                dragAndDropListState.onDragStart(offset)
                            },
                            onDragEnd = { dragAndDropListState.onDragInterrupted() },
                            onDragCancel = { dragAndDropListState.onDragInterrupted() }
                        )
                    },
                state = dragAndDropListState.lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(
                    items = newList,
                    key = { index, item ->
                        item.title
                    }
                ) { index, item ->
                    if (index == 0) {
                        LocationCard(
                            location = item.title,
                            significant = "비 | 강수확률 90%",
                            isCurrentLocation = true,
                            isExtension = false
                        )
                    } else {
                        EditableLocationCard(
                            modifier = Modifier.dragModifier(index, dragAndDropListState),
                            location = item.title,
                            significant = "비 | 강수확률 $index",
                            isCurrentLocation = true,
                            isExtension = false
                        ) {
                            onRemoveLocations(index)
                        }
                    }
                }
            }
        }
    }
}