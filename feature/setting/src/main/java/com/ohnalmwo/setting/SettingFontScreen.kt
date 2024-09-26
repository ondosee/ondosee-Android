package com.ohnalmwo.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.setting.component.SettingSelectComponent
import com.ohnalmwo.setting.component.SettingTitle
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SettingFontScreen(
    hazeState: HazeState,
    onBackClick: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .haze(state = hazeState)
            .statusBarsPadding()
    ) {
        OndoseeBackButton(modifier = Modifier.padding(top = 24.dp)) { onBackClick() }
        SettingTitle(modifier = Modifier.padding(top = 24.dp), title = "테마 설정")
        SettingSelectComponent(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            dropdownList = listOf("시스템 글꼴", "프레젠테이션 (기본글꼴)").toPersistentList(),
            selectedIndex = selectedIndex,
            onItemClick = { selectedIndex = it }
        )
    }
}