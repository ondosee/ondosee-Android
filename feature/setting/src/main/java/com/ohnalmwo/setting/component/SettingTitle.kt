package com.ohnalmwo.setting.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme

@Composable
fun SettingTitle(
    modifier: Modifier,
    title: String
) {
    Text(
        modifier = modifier.padding(start = 20.dp),
        text = title,
        style = OndoseeTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = OndoseeTheme.colors.BLACK
    )
}