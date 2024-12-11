package com.ohnalmwo.setting

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.model.enum.Switch
import com.ohnalmwo.setting.component.AlarmTimeSection
import com.ohnalmwo.setting.component.SettingSwitchButton
import com.ohnalmwo.setting.component.SettingTitle
import com.ohnalmwo.setting.viewmodel.SettingScreenReducer.*
import com.ohnalmwo.setting.viewmodel.SettingViewModel
import com.ohnalmwo.ui.rememberFlowWithLifecycle

@Composable
fun SettingAlarmRoute(
    navigateToBack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(Unit) {
        viewModel.getAlarmState()
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is SettingEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    SettingAlarmScreen(
        state = state,
        onAlarmStateChange = { viewModel.sendEvent(SettingEvent.OnChangeAlarmState(it))},
        navigateToBack = {
                viewModel.sendEffect(SettingEffect.NavigateToBack)
        }
    )
}

@Composable
fun SettingAlarmScreen(
    state: SettingState,
    onAlarmStateChange: (Boolean) -> Unit,
    navigateToBack: () -> Unit
) {
    var hour by remember { mutableIntStateOf(8) }
    var minute by remember { mutableIntStateOf(0) }
    var amPm by remember { mutableStateOf("PM") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding(),
    ) {
        OndoseeBackButton(
            modifier = Modifier.padding(top = 16.dp)
        ) { navigateToBack() }
        SettingTitle(
            modifier = Modifier.padding(top = 24.dp), title = "푸시 알림 설정"
        )
        SettingSwitchButton(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
            text = "푸시 알림 설정",
            isSwitchOn = state.alarmState,
            onCheckedChanged = {
                onAlarmStateChange(it)
            }
        )

        if (state.alarmState) {
            AlarmTimeSection(
                hour = hour,
                minute = minute,
                amPm = amPm,
                onTimeUpdated = { updatedHour, updatedMinute, updatedAmPm ->
                    hour = updatedHour
                    minute = updatedMinute
                    amPm = updatedAmPm
                }
            )
        }
    }
}