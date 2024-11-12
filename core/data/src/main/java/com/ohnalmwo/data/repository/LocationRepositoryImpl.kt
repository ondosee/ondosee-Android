package com.ohnalmwo.data.repository

import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.Location
import com.ohnalmwo.network.datasource.location.LocationDataSource
import com.ohnalmwo.network.mapper.todomain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteLocationDataSource: LocationDataSource
) : LocationRepository {
    override fun getLocationCoordinate(keyword: String, page: Int): Flow<Location> = flow {
        emit(remoteLocationDataSource.getLocationCoordinate(keyword = keyword, page = page).toDomain())
    }
}