package com.ohnalmwo.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.setting.component.SettingOptionTitle
import com.ohnalmwo.setting.component.SettingSelectComponent
import com.ohnalmwo.setting.component.SettingTitle
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SettingThemeScreen(
    onBackClick: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding()
    ) {
        OndoseeBackButton(
            modifier = Modifier.padding(top = 24.dp)
        ) { onBackClick() }
        SettingTitle(
            modifier = Modifier.padding(top = 16.dp),
            title = "테마 설정"
        )
        SettingOptionTitle(
            modifier = Modifier.padding(top = 24.dp),
            optionName = "기본 테마"
        )
        SettingSelectComponent(
            modifier = Modifier.padding(horizontal = 20.dp),
            dropdownList = listOf("시스템 테마 설정", "다크", "화이트").toPersistentList(),
            selectedIndex = selectedIndex,
            onItemClick = { selectedIndex = it }
        )
    }
}