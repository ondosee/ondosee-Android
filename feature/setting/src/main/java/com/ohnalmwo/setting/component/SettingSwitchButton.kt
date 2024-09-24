package com.ohnalmwo.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.button.OndoseeSwitchButton
import com.ohnalmwo.design_system.icons.SettingBellIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun SettingSwitchButton(
    modifier: Modifier,
    icon: Unit,
    text: String,
    isSwitchOn: Boolean,
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingBellIcon(tint = colors.THEME_BLACK)
            Text(
                text = text,
                style = typography.textLarge,
                color = colors.THEME_BLACK,
                fontWeight = FontWeight.Normal
            )
        }
        OndoseeSwitchButton(
            stateOn = 1,
            stateOff = 0,
            initialValue = if (isSwitchOn) 1 else 0,
            onCheckedChanged = {}
        )
    }
}