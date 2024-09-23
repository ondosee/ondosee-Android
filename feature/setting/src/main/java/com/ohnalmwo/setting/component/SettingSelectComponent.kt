package com.ohnalmwo.setting.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.ohnalmwo.design_system.icons.CheckIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SettingSelectComponent(
    modifier: Modifier,
    dropdownList: PersistentList<String>,
    selectedIndex: Int,
) {
    val scrollState = rememberScrollState()
    var dropdownType: Boolean? by remember { mutableStateOf(null) }


    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .background(Color.Transparent)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        dropdownList.onEachIndexed { index, item ->
            dropdownType = when (index) {
                0 -> true
                dropdownList.size - 1 -> false
                else -> null
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        when (dropdownType) {
                            true -> RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp
                            )

                            false -> RoundedCornerShape(
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )

                            else -> RoundedCornerShape(0.dp)
                        }
                    )
                    .background(color = colors.BLACK.copy(alpha = 0.05f))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item,
                    style = typography.textMedium,
                    color = if (index == selectedIndex) colors.PRIMARY else colors.BLACK,
                    fontWeight = FontWeight.Normal
                )
                if (index == selectedIndex) {
                    CheckIcon()
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun SettingSelectComponentPrev() {
    OndoseeTheme {
        SettingSelectComponent(
            modifier = Modifier,
            dropdownList = listOf("1", "2", "3").toPersistentList(),
            selectedIndex = 2
        )
    }
}