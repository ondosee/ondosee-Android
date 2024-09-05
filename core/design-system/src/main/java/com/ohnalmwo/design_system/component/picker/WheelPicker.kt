package com.ohnalmwo.design_system.component.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun <T> WheelPicker(
    width: Dp,
    itemHeight: Dp,
    items: List<T>,
    initialItem: T,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    numberOfDisplayedItems: Int,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> }
) {
    require(items.size == 2) { "아이템 개수는 2개여야 합니다." }

    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberLazyListState(0)
    var lastSelectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(initialItem) {
        val targetIndex = items.indexOf(initialItem)
        lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)
    }

    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayedItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
    ) {
        // 위쪽 빈 공간
        item {
            Spacer(modifier = Modifier.height(itemHeight).fillMaxWidth())
        }

        // 첫 번째 아이템
        item {
            WheelPickerItem(
                item = items[0],
                itemHeight = itemHeight,
                index = 0,
                lastSelectedIndex = lastSelectedIndex,
                onItemSelected = { selectedIndex, selectedItem ->
                    onItemSelected(selectedIndex, selectedItem)
                    lastSelectedIndex = selectedIndex
                },
                itemHalfHeight = itemHalfHeight,
                textStyle = textStyle,
                textColor = textColor,
                selectedTextColor = selectedTextColor,
                numberOfDisplayedItems = numberOfDisplayedItems
            )
        }

        // 두 번째 아이템
        item {
            WheelPickerItem(
                item = items[1],
                itemHeight = itemHeight,
                index = 1,
                lastSelectedIndex = lastSelectedIndex,
                onItemSelected = { selectedIndex, selectedItem ->
                    onItemSelected(selectedIndex, selectedItem)
                    lastSelectedIndex = selectedIndex
                },
                itemHalfHeight = itemHalfHeight,
                textStyle = textStyle,
                textColor = textColor,
                selectedTextColor = selectedTextColor,
                numberOfDisplayedItems = numberOfDisplayedItems
            )
        }

        // 아래쪽 빈 공간
        item {
            Spacer(modifier = Modifier.height(itemHeight).fillMaxWidth())
        }
    }
}