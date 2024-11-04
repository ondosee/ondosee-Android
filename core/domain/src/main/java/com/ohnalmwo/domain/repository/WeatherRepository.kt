package com.ohnalmwo.domain.repository

import com.ohnalmwo.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherSignificant(x: Double, y: Double): Flow<Weather>
}