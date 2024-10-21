package com.ohnalmwo.domain.usecase

import com.ohnalmwo.domain.repository.WeatherRepository
import com.ohnalmwo.model.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherSignificantUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(x: Double, y: Double): Flow<Weather> =
        weatherRepository.getWeatherSignificant(x = x, y = y)
}