package com.ohnalmwo.network.api

import com.ohnalmwo.network.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather/significant")
    suspend fun getWeatherSignificant(
        @Query("x") x: Double,
        @Query("y") y: Double
    ): WeatherResponse
}