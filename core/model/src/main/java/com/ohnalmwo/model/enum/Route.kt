package com.ohnalmwo.model.enum

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Main : Route

    @Serializable
    data object Weekly : Route

    @Serializable
    data object Setting : Route

    @Serializable
    data object SettingTheme : Route

    @Serializable
    data object SettingAlarm : Route
}