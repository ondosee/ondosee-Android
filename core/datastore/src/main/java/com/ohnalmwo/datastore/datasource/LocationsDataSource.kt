package com.ohnalmwo.datastore.datasource

import com.ohnalmwo.model.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationsDataSource {
    fun getLocations(): Flow<List<LocationInfo>>

    suspend fun setLocations(location: LocationInfo)

    suspend fun removeLocations(index: Int)

    suspend fun removeAllLocations()
}