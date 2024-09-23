package com.ohnalmwo.design_system.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun CheckIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = "왼쪽 화살표 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

@Composable
fun ChevronLeftIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_chevron_left),
        contentDescription = "왼쪽 화살표 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

@Composable
fun ChevronRightIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_chevron_right),
        contentDescription = "오른쪽 화살표 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
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

@Composable
fun CloudIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_cloud),
        contentDescription = "구름 아이콘",
        modifier = modifier
    )
}

@Composable
fun DownloadIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_download),
        contentDescription = "구름 아이콘",
        modifier = modifier
    )
}

@Composable
fun RainIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_rain),
        contentDescription = "비 아이콘",
        modifier = modifier
    )
}

@Composable
fun SettingBellIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_setting_bell),
        contentDescription = "설정 알림 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

@Composable
fun SettingDunghillIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_setting_dunghill),
        contentDescription = "설정 초기화 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

@Composable
fun SettingFontIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_setting_font),
        contentDescription = "설정 폰트 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

@Composable
fun SettingThemeIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Image(
        painter = painterResource(id = R.drawable.ic_setting_theme),
        contentDescription = "설정 폰트 아이콘",
        modifier = modifier,
        colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
    )
}

