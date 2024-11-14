package com.ohnalmwo.domain.repository

import com.ohnalmwo.model.Location
import com.ohnalmwo.model.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationCoordinate(keyword: String, page: Int): Flow<Location>

    fun getLocations(): Flow<List<LocationInfo>>

    suspend fun setLocations(location: LocationInfo)

    suspend fun removeLocations(index: Int)

    suspend fun removeAllLocations()
}