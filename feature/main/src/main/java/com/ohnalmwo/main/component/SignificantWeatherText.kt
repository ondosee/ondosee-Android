package com.ohnalmwo.main.component

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.font.FontFamily.freesentation
import com.ohnalmwo.model.enum.BackgroundType
import com.ohnalmwo.ui.getBackgroundColors

@Composable
fun SignificantWeatherText(
    modifier: Modifier = Modifier,
    text: String,
    backgroundType: BackgroundType
) {
    ShadowedText(
        text = text,
        modifier = modifier
    )
    GradientText(
        text = text,
        brush = Brush.verticalGradient(
            colors = listOf(
                getBackgroundColors(type = backgroundType)[0],
                lerp(
                    getBackgroundColors(type = backgroundType)[0],
                    getBackgroundColors(type = backgroundType)[1],
                    0.3f
                )
            )
        ),
        modifier = modifier
    )
    GradientText(
        text = text,
        brush = Brush.verticalGradient(
            colors = listOf(
                colors.WHITE.copy(.3f),
                Color(0xFFBFBFBF).copy(.1f)
            )
        ),
        modifier = modifier
    )
}

@Composable
fun ShadowedText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 80.sp,
            fontFamily = freesentation,
            fontWeight = FontWeight.Black,
            color = colors.BLACK.copy(.1f),
            letterSpacing = 1.2.sp,
        ),
        modifier = modifier.offset(0.dp, 5.dp)
    )
}

@Composable
fun GradientText(
    modifier: Modifier = Modifier,
    text: String,
    brush: Brush
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 80.sp,
            fontFamily = freesentation,
            fontWeight = FontWeight.Black,
            brush = brush,
            letterSpacing = 1.2.sp,
        ),
        modifier = modifier
    )
}