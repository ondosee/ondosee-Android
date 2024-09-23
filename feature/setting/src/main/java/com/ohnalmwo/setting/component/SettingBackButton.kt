package com.ohnalmwo.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.ChevronLeftIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun SettingBackButton(
    modifier: Modifier = Modifier.height(48.dp),
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(start = 15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        ChevronLeftIcon()
        Text(
            text = "돌아가기",
            style = typography.textMedium,
            fontWeight = FontWeight.Normal,
            color = colors.PRIMARY
        )
    }
}