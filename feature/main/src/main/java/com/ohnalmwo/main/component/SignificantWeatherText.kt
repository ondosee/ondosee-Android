package com.ohnalmwo.main.component

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.ohnalmwo.model.WeatherDetail
import com.ohnalmwo.model.enum.Significant
import com.ohnalmwo.ui.getBackgroundColors
import com.ohnalmwo.ui.getSignificantWeatherText

@Composable
fun SignificantWeatherText(
    modifier: Modifier = Modifier,
    weathers: List<WeatherDetail>,
    backgroundType: Significant
) {
    val data = remember(weathers) { weathers[0].timeZone.maxByOrNull { it.value.toIntOrNull() ?: Int.MIN_VALUE }?.value.toString() }

    val text = remember(backgroundType, data) { backgroundType.getSignificantWeatherText(data) }
    val backgroundColors = backgroundType.getBackgroundColors()
    val midColor = remember(backgroundColors) { lerp(backgroundColors[0], backgroundColors[1], .5f) }
    val textStyle = remember {
        TextStyle(
            fontSize = 80.sp,
            fontFamily = freesentation,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.2.sp,
        )
    }

    ShadowedText(
        text = text,
        color = midColor,
        style = textStyle,
        modifier = modifier.offset(0.dp, 5.dp)
    )
    GradientText(
        text = text,
        brush = Brush.verticalGradient(colors = listOf(backgroundColors[0], midColor)),
        style = textStyle,
        modifier = modifier
    )
    GradientText(
        text = text,
        brush = Brush.verticalGradient(
            colors = listOf(
                colors.WHITE.copy(.7f),
                Color(0xFFBFBFBF).copy(.1f)
            )
        ),
        style = textStyle,
        modifier = modifier
    )
}

@Composable
fun ShadowedText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    style: TextStyle
) {
    Text(
        text = text,
        style = style.copy(color = color),
        modifier = modifier
    )
}

@Composable
fun GradientText(
    modifier: Modifier = Modifier,
    text: String,
    brush: Brush,
    style: TextStyle
) {
    Text(
        text = text,
        style = style.copy(brush = brush),
        modifier = modifier
    )
}