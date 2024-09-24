package com.ohnalmwo.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors

@Composable
fun OndoseeButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    style: TextStyle,
    fontWeight: FontWeight,
    state: ButtonState = ButtonState.Normal,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when (state) {
                ButtonState.Normal -> colors.THEME_WHITE.copy(.05f)
                else -> Color.Transparent
            }
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = when (state) {
                ButtonState.Normal -> colors.THEME_WHITE.copy(.2f)
                ButtonState.Transparent -> Color.Transparent
                ButtonState.Primary -> colors.PRIMARY
                ButtonState.NormalDark -> colors.THEME_BLACK.copy(.25f)
            },
            contentColor = when (state) {
                ButtonState.Normal -> colors.THEME_WHITE
                ButtonState.Transparent -> colors.SECONDARY
                ButtonState.Primary -> colors.THEME_WHITE
                ButtonState.NormalDark -> colors.THEME_BLACK.copy(.5f)
            }
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = style,
            fontWeight = fontWeight
        )
    }
}