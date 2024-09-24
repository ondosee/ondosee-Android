package com.ohnalmwo.design_system.component.topbar

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun OndoseeTopBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var currentLocation by rememberSaveable { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp.value
    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.dp.toPx() }

    val swipeableState = rememberSwipeableState(currentLocation)
    val anchors = mapOf(0f to 0, screenWidthPx to 1, 2 * screenWidthPx to 2)

    LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue != currentLocation) {
            currentLocation = swipeableState.currentValue
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 5.dp,
                top = 24.dp,
                bottom = 24.dp
            )
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Horizontal,
                thresholds = { _, _ -> FractionalThreshold(.1f) },
                reverseDirection = true,
                velocityThreshold = 1_000.dp,
                resistance = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            LocationIndicator(
                totalNumber = anchors.size,
                currentLocation = currentLocation,
            )
            Text(
                text = "광주광역시 광산구",
                style = typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = colors.THEME_WHITE,
                modifier = modifier
            )
        }
        IconButton(onClick = {}) {
            content()
        }
    }
}