package com.ohnalmwo.network.datasource.location

import com.ohnalmwo.network.api.LocationAPI
import com.ohnalmwo.network.dto.LocationResponse
import com.ohnalmwo.network.util.ApiHandler
import javax.inject.Inject

class LocationDataSourceImpl @Inject constructor(
    private val locationAPI: LocationAPI
) : LocationDataSource {
    override suspend fun getLocationCoordinate(keyword: String, page: Int): LocationResponse =
        ApiHandler<LocationResponse>().request {
            locationAPI.getLocationCoordinate(keyword = keyword, page = page)
        }
}