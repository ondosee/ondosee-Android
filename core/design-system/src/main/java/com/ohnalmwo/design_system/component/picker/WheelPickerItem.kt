package com.ohnalmwo.design_system.component.picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

@Composable
fun <T> WheelPickerItem(
    item: T,
    itemHeight: Dp,
    index: Int,
    lastSelectedIndex: Int,
    onItemSelected: (index: Int, item: T) -> Unit,
    itemHalfHeight: Float,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    numberOfDisplayedItems: Int
) {
    Box(
        modifier = Modifier
            .height(itemHeight)
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                val y = coordinates.positionInParent().y + itemHalfHeight // 아이템의 중앙 y값
                val parentHalfHeight = (itemHalfHeight * numberOfDisplayedItems) // 화면의 중앙 y값

                // 현재 아이템이 중앙에 위치하는지 확인
                val isSelected = (y > parentHalfHeight - itemHalfHeight && y < parentHalfHeight + itemHalfHeight)
                if (isSelected && lastSelectedIndex != index) {
                    onItemSelected(index, item)
                }
            },
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = item.toString(),
            style = textStyle,
            color = if (lastSelectedIndex == index) selectedTextColor else textColor,
            fontWeight = FontWeight.Bold
        )
    }
}