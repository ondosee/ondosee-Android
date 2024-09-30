package com.ohnalmwo.location.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

fun <T> MutableList<T>.move(from: Int, to: Int) {
    if (from == to) return
    val element = this.removeAt(from)
    this.add(to, element)
}

fun Modifier.dragModifier(index: Int, dragAndDropListState: DragAndDropListState) = composed {
    val isDragging = index == dragAndDropListState.currentIndexOfDraggedItem
    val offsetOrNull = dragAndDropListState.elementDisplacement.takeIf { isDragging }

    Modifier
        .zIndex(if (isDragging) 1f else 0f)
        .graphicsLayer {
            translationY = offsetOrNull ?: 0f
            scaleX = if (isDragging) 1.05f else 1f
            scaleY = if (isDragging) 1.05f else 1f
        }
}