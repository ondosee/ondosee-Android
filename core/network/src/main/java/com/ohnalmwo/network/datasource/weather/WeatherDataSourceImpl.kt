package com.ohnalmwo.network.datasource.weather

import com.ohnalmwo.network.api.WeatherAPI
import com.ohnalmwo.network.dto.WeatherResponse
import com.ohnalmwo.network.util.ApiHandler
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    private val weatherAPI: WeatherAPI
) : WeatherDataSource {
    override suspend fun getWeatherSignificant(x: Double, y: Double): WeatherResponse =
        ApiHandler<WeatherResponse>().request {
            weatherAPI.getWeatherSignificant(x = x, y = y)
        }
}