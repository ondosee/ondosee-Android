package com.ohnalmwo.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.ChevronRightIcon
import com.ohnalmwo.design_system.icons.SettingBellIcon
import com.ohnalmwo.design_system.icons.SettingDunghillIcon
import com.ohnalmwo.design_system.icons.SettingFontIcon
import com.ohnalmwo.design_system.icons.SettingThemeIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

enum class SettingButtonType(val value: String) {
    Theme("테마 설정"),
    Font("글꼴 설정"),
    Alarm("푸시 알림 설정"),
    ClearAll("모든 기록 삭제")
}

@Composable
internal fun SettingButton(
    modifier: Modifier,
    buttonType: SettingButtonType,
    onClick: () -> Unit
) {
    val text = when(buttonType) {
        SettingButtonType.Theme -> SettingButtonType.Theme.value
        SettingButtonType.Font -> SettingButtonType.Font.value
        SettingButtonType.Alarm -> SettingButtonType.Alarm.value
        SettingButtonType.ClearAll -> SettingButtonType.ClearAll.value
    }

    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                when(buttonType) {
                    SettingButtonType.Theme -> SettingThemeIcon(tint = colors.BLACK)
                    SettingButtonType.Font -> SettingFontIcon(tint = colors.BLACK)
                    SettingButtonType.Alarm -> SettingBellIcon(tint = colors.BLACK)
                    SettingButtonType.ClearAll -> SettingDunghillIcon(tint = colors.WARNING)
                }
                Text(
                    text = text,
                    style = typography.textMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (buttonType != SettingButtonType.ClearAll) colors.BLACK else colors.WARNING
                )
            }

            ChevronRightIcon(
                tint = if (buttonType != SettingButtonType.ClearAll) colors.BLACK else colors.WARNING
            )
        }
    }
}