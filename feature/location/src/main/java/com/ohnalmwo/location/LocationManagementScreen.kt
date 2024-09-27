package com.ohnalmwo.location

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.EditableLocationCard
import com.ohnalmwo.location.component.LocationCard
import com.ohnalmwo.location.component.LocationText
import com.ohnalmwo.location.util.dragModifier
import com.ohnalmwo.location.util.move
import com.ohnalmwo.location.util.rememberDragAndDropListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun LocationManagementScreen(
    navigateToBack: () -> Unit
) {
    val list = remember { mutableStateListOf(1, 2, 3, 4, 5) }
    val lazyListState = rememberLazyListState()
    val dragAndDropListState =
        rememberDragAndDropListState(lazyListState) { from, to ->
            if (from != 0 && to != 0) {
                list.move(from, to)
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
                    items = list,
                    key = { index, data ->
                        data
                    }
                ) { index, item ->
                    if (index == 0) {
                        LocationCard(
                            location = item.toString(),
                            significant = index.toString(),
                            isCurrentLocation = true,
                            isExtension = false
                        )
                    } else {
                        EditableLocationCard(
                            modifier = Modifier.dragModifier(index, dragAndDropListState),
                            location = item.toString(),
                            significant = index.toString(),
                            isCurrentLocation = true,
                            isExtension = false
                        ) {
                            list.removeAt(index)
                        }
                    }
                }
            }
        }
    }
}