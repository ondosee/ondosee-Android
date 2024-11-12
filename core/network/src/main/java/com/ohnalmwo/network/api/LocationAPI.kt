package com.ohnalmwo.network.api

import com.ohnalmwo.network.dto.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationAPI {
    @GET("location/coordinate")
    suspend fun getLocationCoordinate(
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 1,
    ): LocationResponse
}