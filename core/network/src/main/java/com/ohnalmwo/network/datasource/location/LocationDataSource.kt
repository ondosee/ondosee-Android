package com.ohnalmwo.network.datasource.location

import com.ohnalmwo.network.dto.LocationResponse

interface LocationDataSource {
    suspend fun getLocationCoordinate(keyword: String, page: Int): LocationResponse
}