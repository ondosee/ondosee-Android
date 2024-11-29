package com.ohnalmwo.design_system.component.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShimmerBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier.shimmerEffect())
}