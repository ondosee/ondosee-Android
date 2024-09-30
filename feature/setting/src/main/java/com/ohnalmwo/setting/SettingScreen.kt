package com.ohnalmwo.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.setting.component.SettingButton
import com.ohnalmwo.setting.component.SettingButtonType
import com.ohnalmwo.setting.component.SettingOptionTitle
import com.ohnalmwo.setting.component.SettingTitle
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun SettingScreen(
    hazeState: HazeState,
    navigateToSettingTheme: () -> Unit,
    navigateToSettingFont: () -> Unit,
    navigateToSettingAlarm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .haze(hazeState)
            .statusBarsPadding()
    ) {
        SettingTitle(
            modifier = Modifier.padding(top = 24.dp),
            title = "설정"
        )
        SettingOptionTitle(
            modifier = Modifier.padding(top = 24.dp),
            optionName = "테마 설정"
        )
        SettingButton(
            modifier = Modifier.padding(top = 24.dp),
            buttonType = SettingButtonType.Theme
        ) { navigateToSettingTheme() }
        SettingButton(
            modifier = Modifier.padding(top = 32.dp),
            buttonType = SettingButtonType.Font
        ) { navigateToSettingFont() }
        SettingOptionTitle(
            modifier = Modifier.padding(top = 56.dp),
            optionName = "개인정보 설정"
        )
        SettingButton(
            modifier = Modifier.padding(top = 24.dp),
            buttonType = SettingButtonType.Alarm
        ) { navigateToSettingAlarm() }
        SettingButton(
            modifier = Modifier.padding(top = 32.dp),
            buttonType = SettingButtonType.ClearAll
        ) {}
    }
}