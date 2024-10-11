package com.ohnalmwo.model.enum

import androidx.compose.runtime.Immutable

@Immutable
enum class ThemeType(val value: String, val kr: String) {
    SYSTEM("System", "시스템 테마 설정"),
    DARK("Dark", "다크(기본)"),
    LIGHT("Light", "라이트")
}