package com.ohnalmwo.design_system.component.shimmer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousModifierThen")
fun Modifier.shimmerEffect(
    targetValue: Float = 1_300f,
    shape: Shape = RoundedCornerShape(4.dp)
) = composed {
    val brush = ShimmerBrush(targetValue = targetValue)
    this.then(background(brush, shape))
}