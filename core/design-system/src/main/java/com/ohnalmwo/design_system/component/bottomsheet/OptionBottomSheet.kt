package com.ohnalmwo.design_system.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionBottomSheet(
    modifier: Modifier = Modifier,
    closeSheet: () -> Unit,
    navigateToLocationManagement: () -> Unit,
    navigateToAddLocation: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = colors.BACKGROUND
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 8.dp, bottom = 24.dp)
        ) {
            BottomSheetHeader(
                modifier = Modifier,
                closeSheet = closeSheet,
                title = "위치 옵션",
            )
            OptionBottomSheetComponent(
                modifier = Modifier.padding(top = 16.dp, end = 12.dp),
                text = "등록된 위치 관리"
            ) {
                navigateToLocationManagement()
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                color = colors.WHITE.copy(.15f)
            )
            OptionBottomSheetComponent(
                modifier = Modifier.padding(end = 12.dp),
                text = "새 위치 등록"
            ) {
                navigateToAddLocation()
            }
        }
    }
}

@Composable
fun OptionBottomSheetComponent(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = typography.textMedium,
            color = colors.THEME_BLACK,
            fontWeight = FontWeight.SemiBold
        )
    }
}