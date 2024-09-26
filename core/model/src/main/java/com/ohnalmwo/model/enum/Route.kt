package com.ohnalmwo.model.enum

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Main : Route

    @Serializable
    data object Location : Route

    @Serializable
    data object LocationManagement : Route

    @Serializable
    data object AddLocation : Route

    @Serializable
    data object Weekly : Route

    @Serializable
    data object Setting : Route
}