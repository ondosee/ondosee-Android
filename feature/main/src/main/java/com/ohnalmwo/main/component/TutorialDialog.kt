package com.ohnalmwo.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ohnalmwo.design_system.component.button.ButtonState
import com.ohnalmwo.design_system.component.button.OndoseeButton
import com.ohnalmwo.design_system.component.picker.InfiniteWheelPicker
import com.ohnalmwo.design_system.component.picker.WheelPicker
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.design_system.theme.font.FontFamily.freesentation
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild

@Composable
fun TutorialDialog(
    hazeState: HazeState,
    openDialog: Boolean,
    onStateChange: (Boolean) -> Unit,
    onDismissClick: () -> Unit,
    onCheckClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(openDialog) }
    var hour by remember { mutableIntStateOf(8) }
    var minute by remember { mutableIntStateOf(0) }
    var amPm by remember { mutableStateOf("PM") }
    var isTimeSetting by remember { mutableStateOf(false) }

    if (openDialog) {
        Dialog(
            onDismissRequest = { openDialog = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            DialogContent(
                hazeState = hazeState,
                isTimeSetting = isTimeSetting,
                hour = hour,
                minute = minute,
                amPm = amPm,
                onHourChange = { hour = it },
                onMinuteChange = { minute = it },
                onAmPmChange = { amPm = it },
                onTimeSettingChange = { isTimeSetting = it },
                onCheckClick = onCheckClick,
                onDismissClick = onDismissClick
            )
        }
    } else {
        onStateChange(openDialog)
    }
}

@Composable
private fun DialogContent(
    hazeState: HazeState,
    isTimeSetting: Boolean,
    hour: Int,
    minute: Int,
    amPm: String,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onAmPmChange: (String) -> Unit,
    onTimeSettingChange: (Boolean) -> Unit,
    onCheckClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                style = HazeStyle(
                    tint = colors.BLACK.copy(.1f),
                    blurRadius = 10.dp
                )
            )
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TitleText("매일 날씨를 알려드릴까요?")
        DescriptionText("On°C가 매일 알림을 보내드려요.\n알림을 받으실 시간을 알려주실래요?")
        TimeDisplay(
            hour = hour,
            minute = minute,
            amPm = amPm,
            isTimeSetting = isTimeSetting
        )
        if (isTimeSetting) {
            TimePickerSection(
                hour = hour,
                minute = minute,
                amPm = amPm,
                onHourChange = onHourChange,
                onMinuteChange = onMinuteChange,
                onAmPmChange = onAmPmChange,
                onCheckClick = onCheckClick
            )
        } else {
            ActionButtons(
                onTimeSettingChange = { onTimeSettingChange(true) },
                onDismissClick = onDismissClick
            )
        }
    }
}

@Composable
private fun TitleText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 27.sp,
            lineHeight = 32.4.sp,
            fontFamily = freesentation,
        ),
        fontWeight = FontWeight.Bold,
        color = colors.WHITE
    )
}

@Composable
private fun DescriptionText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            fontFamily = freesentation,
        ),
        fontWeight = FontWeight.Medium,
        color = colors.TERTIARY,
        modifier = Modifier.padding(bottom = 40.dp)
    )
}

@Composable
private fun TimeDisplay(
    hour: Int,
    minute: Int,
    amPm: String,
    isTimeSetting: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.WHITE.copy(.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = colors.WHITE.copy(.05f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$hour : ${"%02d".format(minute)}",
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (isTimeSetting) colors.PRIMARY else colors.THEME_WHITE
        )
        Text(
            text = amPm,
            style = typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = if (isTimeSetting) colors.PRIMARY else colors.THEME_WHITE,
            modifier = Modifier.padding(start = 32.dp)
        )
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
    onCheckClick: () -> Unit
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
            textColor = colors.TERTIARY.copy(.2f),
            selectedTextColor = colors.WHITE,
            onItemSelected = { _, item -> onHourChange(item) }
        )
        InfiniteWheelPicker(
            width = 48.dp,
            itemHeight = 52.dp,
            items = (0..59).map { "%02d".format(it) },
            initialItem = "%02d".format(minute),
            textStyle = typography.titleLarge,
            textColor = colors.TERTIARY.copy(.2f),
            selectedTextColor = colors.WHITE,
            onItemSelected = { _, item -> onMinuteChange(item.toInt()) }
        )
        WheelPicker(
            width = 48.dp,
            itemHeight = 52.dp,
            items = listOf("AM", "PM"),
            initialItem = amPm,
            textStyle = typography.titleLarge,
            textColor = colors.TERTIARY.copy(.2f),
            selectedTextColor = colors.WHITE,
            numberOfDisplayedItems = 3,
            onItemSelected = { _, item -> onAmPmChange(item) }
        )
    }
    OndoseeButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = "완료",
        style = typography.textLarge,
        fontWeight = FontWeight.Medium,
        state = ButtonState.Transparent
    ) {
        onCheckClick()
    }
}

@Composable
private fun ActionButtons(
    onTimeSettingChange: () -> Unit,
    onDismissClick: () -> Unit
) {
    OndoseeButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 228.dp),
        text = "알림 설정하기",
        style = typography.titleSmall,
        fontWeight = FontWeight.Bold,
    ) {
        onTimeSettingChange()
    }
    OndoseeButton(
        modifier = Modifier.fillMaxWidth(),
        text = "알림 사용 안함",
        style = typography.textLarge,
        fontWeight = FontWeight.Medium,
        state = ButtonState.Transparent
    ) {
        onDismissClick()
    }
}