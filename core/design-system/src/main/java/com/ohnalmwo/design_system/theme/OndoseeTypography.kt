package com.ohnalmwo.design_system.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ohnalmwo.design_system.theme.font.FontFamily.freesentation


object OndoseeTypography {
    @Stable
    val titleLarge = TextStyle(
        fontFamily = freesentation,
        fontSize = 30.sp,
        lineHeight = 36.sp
    )

    @Stable
    val titleMedium = TextStyle(
        fontFamily = freesentation,
        fontSize = 25.sp,
        lineHeight = 30.sp
    )

    @Stable
    val titleSmall = TextStyle(
        fontFamily = freesentation,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )

    @Stable
    val textLarge = TextStyle(
        fontFamily = freesentation,
        fontSize = 17.sp,
        lineHeight = 20.sp
    )

    @Stable
    val textMedium = TextStyle(
        fontFamily = freesentation,
        fontSize = 15.sp,
        lineHeight = 18.sp
    )

    @Stable
    val textSmall = TextStyle(
        fontFamily = freesentation,
        fontSize = 13.sp,
        lineHeight = 16.sp
    )

    @Stable
    val caption = TextStyle(
        fontFamily = freesentation,
        fontSize = 10.sp,
        lineHeight = 12.sp
    )
}