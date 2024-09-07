package com.ohnalmwo.design_system.component.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun <T> InfiniteWheelPicker(
    width: Dp,
    itemHeight: Dp,
    items: List<T>,
    initialItem: T,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    numberOfDisplayedItems: Int = 5,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> }
) {
    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberLazyListState(0)
    var lastSelectedIndex by remember { mutableIntStateOf(0) }
    var itemsState by remember { mutableStateOf(items) }

    LaunchedEffect(items) {
        var targetIndex = items.indexOf(initialItem) - (numberOfDisplayedItems / 2) // 초기값 인덱스(가운데에 위치시키기 위함(numberOfDisplayedItems / 2))
        targetIndex += ((Int.MAX_VALUE / 2) / items.size) * items.size // 리스트에서의 실제 초기값 인덱스
        itemsState = items
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
        items(
            count = Int.MAX_VALUE,
            key = { it }
        ) { index ->
            val item = itemsState[index % itemsState.size]
            WheelPickerItem(
                item = item,
                itemHeight = itemHeight,
                index = index,
                lastSelectedIndex = lastSelectedIndex,
                onItemSelected = { selectedIndex, selectedItem ->
                    onItemSelected(selectedIndex % items.size, selectedItem)
                    lastSelectedIndex = selectedIndex
                },
                itemHalfHeight = itemHalfHeight,
                textStyle = textStyle,
                textColor = textColor,
                selectedTextColor = selectedTextColor,
                numberOfDisplayedItems = numberOfDisplayedItems
            )
        }
    }
}