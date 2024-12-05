package com.ohnalmwo.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
internal fun AlarmTimeSection(
    hour: Int,
    minute: Int,
    amPm: String,
    onTimeUpdated: (Int, Int, String) -> Unit
) {
    var isTimeSetting by remember { mutableStateOf(false) }
    SettingAlarmTimeButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        text = "알림 시간 설정",
        alarmTime = "$hour:${"%02d".format(minute)} $amPm",
        isTimeSetting = isTimeSetting,
        onCheckedChanged = { isTimeSetting = it }
    )

    if (isTimeSetting) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TimePickerSection(
                hour = hour,
                minute = minute,
                amPm = amPm,
                onTimeUpdated = { updatedHour, updatedMinute, updatedAmPm ->
                    isTimeSetting = false
                    onTimeUpdated(updatedHour, updatedMinute, updatedAmPm)
                },
                onCancel = { isTimeSetting = false }
            )
        }
    }
}

@Composable
private fun TimePickerSection(
    hour: Int,
    minute: Int,
    amPm: String,
    onTimeUpdated: (Int, Int, String) -> Unit,
    onCancel: () -> Unit
) {
    var tempHour by remember { mutableIntStateOf(hour) }
    var tempMinute by remember { mutableIntStateOf(minute) }
    var tempAmPm by remember { mutableStateOf(amPm) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfiniteWheelPicker(
                width = 48.dp,
                itemHeight = 52.dp,
                items = (1..12).toList(),
                initialItem = tempHour,
                textStyle = typography.titleLarge,
                textColor = colors.THEME_BLACK.copy(0.2f),
                selectedTextColor = colors.PRIMARY,
                onItemSelected = { _, item -> tempHour = item }
            )
            InfiniteWheelPicker(
                width = 48.dp,
                itemHeight = 52.dp,
                items = (0..59).map { "%02d".format(it) },
                initialItem = "%02d".format(tempMinute),
                textStyle = typography.titleLarge,
                textColor = colors.THEME_BLACK.copy(0.2f),
                selectedTextColor = colors.PRIMARY,
                onItemSelected = { _, item -> tempMinute = item.toInt() }
            )
            WheelPicker(
                width = 48.dp,
                itemHeight = 52.dp,
                items = listOf("AM", "PM"),
                initialItem = tempAmPm,
                textStyle = typography.titleLarge,
                textColor = colors.THEME_BLACK.copy(0.2f),
                selectedTextColor = colors.PRIMARY,
                numberOfDisplayedItems = 3,
                onItemSelected = { _, item -> tempAmPm = item }
            )
        }
        OndoseeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            text = "적용",
            style = typography.textLarge,
            fontWeight = FontWeight.Medium,
            state = ButtonState.Primary,
            onClick = {
                onTimeUpdated(tempHour, tempMinute, tempAmPm)
            }
        )
        OndoseeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp, end = 20.dp),
            text = "취소",
            style = typography.textLarge,
            fontWeight = FontWeight.Medium,
            state = ButtonState.NormalDark,
            onClick = onCancel
        )
    }
}