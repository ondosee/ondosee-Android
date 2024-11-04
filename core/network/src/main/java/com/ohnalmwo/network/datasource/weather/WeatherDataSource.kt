package com.ohnalmwo.network.datasource.weather

import com.ohnalmwo.network.dto.WeatherResponse

interface WeatherDataSource {
    suspend fun getWeatherSignificant(x: Double, y: Double): WeatherResponse
}