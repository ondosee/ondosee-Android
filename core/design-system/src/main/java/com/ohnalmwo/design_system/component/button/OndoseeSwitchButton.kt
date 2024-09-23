package com.ohnalmwo.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun OndoseeSwitchButton(
    height: Dp = 32.dp,
    width: Dp = 52.dp,
    circleSize: Dp = 26.dp,
    circleHorizontalButtonPadding: Dp = 3.dp,
    circleVerticalButtonPadding: Dp = 2.dp,
    switchOnBackground: Color = colors.ABLE,
    switchOffBackground: Color = colors.DISABLE,
    stateOn: Int,
    stateOff: Int,
    initialValue: Int,
    onCheckedChanged: (checked: Boolean) -> Unit
) {
    var clickListener by remember { mutableStateOf(false) }
    var isActivation by remember { mutableStateOf(false) }

    val swipeableState =
        rememberSwipeableState(initialValue = initialValue, confirmStateChange = { true })

    val sizePx = with(LocalDensity.current) { (width - height).toPx() }
    val anchors = mapOf(0f to stateOff, sizePx to stateOn)
    val scope = rememberCoroutineScope()

    LaunchedEffect("init") {
        if(initialValue == stateOn) {
            clickListener = true
            isActivation = true
        }
    }

    LaunchedEffect(isActivation) {
        if (isActivation) onCheckedChanged(true) else onCheckedChanged(false)
    }

    Box(
        modifier = Modifier.clickable {
            clickListener = !clickListener
            scope.launch {
                if (clickListener) {
                    swipeableState.animateTo(stateOn)
                } else {
                    swipeableState.animateTo(stateOff)
                }
            }
        }
    ) {
        Row(
            modifier = Modifier
                .height(height)
                .width(width)
                .clip(RoundedCornerShape(height))
                .background(
                    if (swipeableState.currentValue == stateOff) {
                        isActivation = false
                        switchOffBackground
                    } else {
                        isActivation = true
                        switchOnBackground
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Horizontal
                    )
                    .padding(
                        horizontal = circleHorizontalButtonPadding,
                        vertical = circleVerticalButtonPadding,
                    )
                    .clip(RoundedCornerShape(50))
                    .background(color = Color.White)
                    .size(circleSize)
            )
        }
    }
}