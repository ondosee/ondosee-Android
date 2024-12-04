package com.ohnalmwo.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.ClockIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun SettingAlarmTimeButton(
    modifier: Modifier,
    text: String,
    alarmTime: String,
    isTimeSetting: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clickable { onCheckedChanged(!isTimeSetting) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClockIcon(tint = colors.THEME_BLACK)
            Text(
                text = text,
                style = typography.textLarge,
                color = colors.THEME_BLACK,
                fontWeight = FontWeight.Normal
            )
        }
        Text(
            text = alarmTime,
            style = typography.textLarge,
            color = if(isTimeSetting) colors.PRIMARY else colors.THEME_BLACK,
            fontWeight = FontWeight.Normal
        )
    }
}