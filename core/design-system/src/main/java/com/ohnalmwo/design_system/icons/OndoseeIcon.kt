package com.ohnalmwo.design_system.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.ohnalmwo.design_system.R

@Composable
fun MenuIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_menu),
        contentDescription = "메뉴 아이콘",
        modifier = modifier
    )
}

@Composable
fun CurrentLocationIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_current_location),
        contentDescription = "현재 위치 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}