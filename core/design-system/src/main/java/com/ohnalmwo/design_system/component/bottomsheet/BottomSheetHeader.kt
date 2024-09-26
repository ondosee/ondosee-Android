package com.ohnalmwo.design_system.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.icons.CloseIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun BottomSheetHeader(
    modifier: Modifier = Modifier,
    title: String,
    closeSheet: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = colors.WHITE
        )
        IconButton(onClick = closeSheet) {
            CloseIcon(
                tint = colors.WHITE
            )
        }
    }
}