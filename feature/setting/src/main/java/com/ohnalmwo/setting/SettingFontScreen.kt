package com.ohnalmwo.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.setting.component.SettingBackButton
import com.ohnalmwo.setting.component.SettingOptionTitle
import com.ohnalmwo.setting.component.SettingSelectComponent
import com.ohnalmwo.setting.component.SettingTitle
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SettingFontScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = colors.BACKGROUND)
    ) {
        SettingBackButton(modifier = Modifier.padding(top = 24.dp)) { onBackClick() }
        SettingTitle(modifier = Modifier.padding(top = 24.dp), title = "테마 설정")
        SettingSelectComponent(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp), dropdownList = listOf("시스템 글꼴","프레젠테이 (기본글꼴)").toPersistentList(), selectedIndex = 1)
    }
}