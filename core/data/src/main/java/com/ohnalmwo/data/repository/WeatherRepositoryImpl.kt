package com.ohnalmwo.data.repository

import com.ohnalmwo.domain.repository.WeatherRepository
import com.ohnalmwo.model.Weather
import com.ohnalmwo.network.datasource.weather.WeatherDataSource
import com.ohnalmwo.network.mapper.todomain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataSource: WeatherDataSource
) : WeatherRepository {
    override fun getWeatherSignificant(x: Double, y: Double): Flow<Weather> = flow {
        remoteWeatherDataSource.getWeatherSignificant(x = x, y = y).toDomain()
    }
}