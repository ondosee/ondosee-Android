package com.ohnalmwo.design_system.component.shimmer

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors

@Composable
fun ShimmerBrush(
    targetValue: Float
): Brush {
    val shimmerColors = listOf(
        colors.WHITE.copy(alpha = .5f),
        colors.WHITE.copy(alpha = .45f),
        colors.WHITE.copy(alpha = .4f),
        colors.WHITE.copy(alpha = .45f),
        colors.WHITE.copy(alpha = .5f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1_000,
                easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Restart
        )
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}