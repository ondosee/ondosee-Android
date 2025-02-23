package com.ohnalmwo.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun OndoseeDialog(
    openDialog: Boolean,
    title: String,
    content: String,
    firstText: String,
    secondText: String?,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(openDialog) }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            Card(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.background(colors.BACKGROUND)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colors.BACKGROUND)
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            color = colors.THEME_BLACK,
                            style = typography.textLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = content,
                            color = colors.SECONDARY,
                            style = typography.textLarge,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(end = 8.dp)
                            .background(colors.BACKGROUND),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp),
                            onClick = {
                                openDialog = false
                                onFirstClick()
                            }
                        ) {
                            Text(
                                text = firstText,
                                color = colors.INFORMATION,
                                style = typography.textLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        secondText?.let {
                            TextButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(60.dp),
                                onClick = {
                                    openDialog = false
                                    onSecondClick()
                                }
                            ) {
                                Text(
                                    text = secondText,
                                    color = colors.INFORMATION,
                                    style = typography.textLarge,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}