package com.ohnalmwo.data.repository

import com.ohnalmwo.datastore.datasource.location.LocationsDataSource
import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.Location
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.network.datasource.location.LocationDataSource
import com.ohnalmwo.network.mapper.todomain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteLocationDataSource: LocationDataSource,
    private val localLocationDataSource: LocationsDataSource
) : LocationRepository {
    override fun getLocationCoordinate(keyword: String, page: Int): Flow<Location> = flow {
        emit(remoteLocationDataSource.getLocationCoordinate(keyword = keyword, page = page).toDomain())
    }

    override fun getLocations(): Flow<List<LocationInfo>> =
        localLocationDataSource.getLocations()

    override suspend fun setLocations(location: LocationInfo) {
        localLocationDataSource.setLocations(location = location)
    }

    override suspend fun updateAllLocations(locations: List<LocationInfo>) {
        localLocationDataSource.updateAllLocations(locations = locations)
    }

    override suspend fun removeLocations(index: Int) {
        localLocationDataSource.removeLocations(index = index)
    }

    override suspend fun removeAllLocations() {
        localLocationDataSource.removeAllLocations()
    }
}