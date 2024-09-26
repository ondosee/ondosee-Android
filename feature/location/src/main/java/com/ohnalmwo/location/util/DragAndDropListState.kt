package com.ohnalmwo.location.util

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

@Composable
fun rememberDragAndDropListState(
	lazyListState: LazyListState,
	onMove: (Int, Int) -> Unit
): DragAndDropListState {
	return remember { DragAndDropListState(lazyListState, onMove) }
}

class DragAndDropListState(
	val lazyListState: LazyListState,
	private val onMove: (Int, Int) -> Unit
) {
	private var draggingDistance by mutableFloatStateOf(0f)
	private var initialDraggingElement by mutableStateOf<LazyListItemInfo?>(null)
	var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)
	private val initialOffsets: Pair<Int, Int>?
		get() = initialDraggingElement?.let { Pair(it.offset, it.offsetEnd) }
	val elementDisplacement: Float?
		get() = currentIndexOfDraggedItem?.let {
			lazyListState.getVisibleItemInfo(it)
		}?.let { itemInfo ->
			(initialDraggingElement?.offset ?: 0f).toFloat() + draggingDistance - itemInfo.offset
		}
	private val currentElement: LazyListItemInfo?
		get() = currentIndexOfDraggedItem?.let {
			lazyListState.getVisibleItemInfo(it)
		}

	fun onDragStart(offset: Offset) {
		lazyListState.layoutInfo.visibleItemsInfo
			.firstOrNull { item -> offset.y.toInt() in item.offset..item.offsetEnd }
			?.also {
				initialDraggingElement = it
				currentIndexOfDraggedItem = it.index
			}
	}

	fun onDragInterrupted() {
		initialDraggingElement = null
		currentIndexOfDraggedItem = null
		draggingDistance = 0f
	}

	fun onDrag(offset: Offset) {
		draggingDistance += offset.y

		initialOffsets?.let { (top, bottom) ->
			val startOffset = top.toFloat() + draggingDistance
			val endOffset = bottom.toFloat() + draggingDistance

			currentElement?.let { current ->
				lazyListState.layoutInfo.visibleItemsInfo
					.filterNot { item ->
						item.offsetEnd < startOffset || item.offset > endOffset || current.index == item.index
					}
					.firstOrNull { item ->
						val delta = startOffset - current.offset
						when {
							delta < 0 -> item.offset > startOffset
							else -> item.offsetEnd < endOffset
						}
					}
			}?.also { item ->
				currentIndexOfDraggedItem?.let { current ->
					onMove.invoke(current, item.index)
				}
				currentIndexOfDraggedItem = item.index
			}
		}
	}

	fun checkOverscroll(): Float {
		return initialDraggingElement?.let {
			val startOffset = it.offset + draggingDistance
			val endOffset = it.offsetEnd + draggingDistance

			return@let when {
				draggingDistance > 0 -> {
					(endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff -> diff > 0 }

				}

				draggingDistance < 0 -> {
					(startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff -> diff < 0 }
				}

				else -> null
			}
		} ?: 0f
	}

	private fun LazyListState.getVisibleItemInfo(itemPosition: Int): LazyListItemInfo? {
		return this.layoutInfo.visibleItemsInfo.getOrNull(itemPosition - this.firstVisibleItemIndex)
	}

	private val LazyListItemInfo.offsetEnd: Int
		get() = this.offset + this.size
}