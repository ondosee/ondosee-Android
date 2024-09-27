package com.ohnalmwo.design_system.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.CurrentLocationIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors

@Composable
fun LocationIndicator(
    modifier: Modifier = Modifier,
    totalNumber: Int,
    currentLocation: Int,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = modifier.height(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(totalNumber) { index ->
                LocationDot(
                    modifier = Modifier.size(12.dp),
                    isSelected = index == currentLocation,
                    isFirstDot = index == 0
                )
            }
        }
    }
}

@Composable
fun LocationDot(
    modifier: Modifier,
    isSelected: Boolean,
    isFirstDot: Boolean
) {
    val color = if (isSelected) colors.PRIMARY else colors.WHITE.copy(.75f)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (isFirstDot) {
            CurrentLocationIcon(tint = color)
        } else {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(color = color, shape = CircleShape)
            )
        }
    }
}