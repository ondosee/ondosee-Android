package com.ohnalmwo.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.button.ButtonState
import com.ohnalmwo.design_system.component.button.OndoseeButton
import com.ohnalmwo.design_system.component.picker.InfiniteWheelPicker
import com.ohnalmwo.design_system.component.picker.WheelPicker
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.setting.component.SettingAlarmTimeButton
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.setting.component.SettingSwitchButton
import com.ohnalmwo.setting.component.SettingTitle

@Composable
fun SettingAlarmScreen(
    onBackClick: () -> Unit
) {
    var isAlarmOn by remember { mutableStateOf(true) }

    var hour by remember { mutableIntStateOf(8) }
    var minute by remember { mutableIntStateOf(0) }
    var amPm by remember { mutableStateOf("PM") }
    var isTimeSetting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding(),
    ) {
        OndoseeBackButton(
            modifier = Modifier.padding(top = 24.dp)
        ) { onBackClick() }
        SettingTitle(
            modifier = Modifier.padding(top = 24.dp),
            title = "푸시 알림 설정"
        )
        SettingSwitchButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp), icon = Unit, text = "푸시 알림 설정", isSwitchOn = isAlarmOn
        )
        SettingAlarmTimeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 48.dp),
            icon = Unit,
            text = "알림 시간 설정",
            alarmTime = "8:00 PM",
            isAlarmOn = isAlarmOn
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TimePickerSection(
                hour = hour,
                minute = minute,
                amPm = amPm,
                onHourChange = { hour = it },
                onMinuteChange = { minute = it },
                onAmPmChange = { amPm = it },
            )
        }
        OndoseeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            text = "적용",
            style = typography.textLarge,
            fontWeight = FontWeight.Medium,
            state = ButtonState.Primary
        ) {}

        OndoseeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp, end = 20.dp),
            text = "취소",
            style = typography.textLarge,
            fontWeight = FontWeight.Medium,
            state = ButtonState.NormalDark
        ) {}
    }
}

@Composable
private fun TimePickerSection(
    hour: Int,
    minute: Int,
    amPm: String,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onAmPmChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfiniteWheelPicker(
            width = 48.dp,
            itemHeight = 52.dp,
            items = (1..12).toList(),
            initialItem = hour,
            textStyle = typography.titleLarge,
            textColor = colors.BLACK.copy(.2f),
            selectedTextColor = colors.PRIMARY,
            onItemSelected = { _, item -> onHourChange(item) }
        )
        InfiniteWheelPicker(
            width = 48.dp,
            itemHeight = 52.dp,
            items = (0..59).map { "%02d".format(it) },
            initialItem = "%02d".format(minute),
            textStyle = typography.titleLarge,
            textColor = colors.BLACK.copy(.2f),
            selectedTextColor = colors.PRIMARY,
            onItemSelected = { _, item -> onMinuteChange(item.toInt()) }
        )
        WheelPicker(
            width = 48.dp,
            itemHeight = 52.dp,
            items = listOf("AM", "PM"),
            initialItem = amPm,
            textStyle = typography.titleLarge,
            textColor = colors.BLACK.copy(.2f),
            selectedTextColor = colors.PRIMARY,
            numberOfDisplayedItems = 3,
            onItemSelected = { _, item -> onAmPmChange(item) }
        )
    }
}