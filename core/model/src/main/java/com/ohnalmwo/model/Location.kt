package com.ohnalmwo.model

import androidx.compose.runtime.Immutable

@Immutable
data class Location(
    val page: PageInfo,
    val locations: List<LocationInfo>
) {
    companion object {
        fun default() = Location(
            page = PageInfo.default(),
            locations = emptyList()
        )
    }
}

@Immutable
data class PageInfo(
    val total: Int,
    val current: Int
) {
    companion object {
        fun default() = PageInfo(
            total = 0,
            current = 0
        )
    }
}

@Immutable
data class LocationInfo(
    val title: String,
    val x: String,
    val y: String,
)