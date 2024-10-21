package com.ohnalmwo.network.di

import com.ohnalmwo.network.datasource.weather.WeatherDataSource
import com.ohnalmwo.network.datasource.weather.WeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindWeatherDataSource(
        weatherDataSourceImpl: WeatherDataSourceImpl
    ): WeatherDataSource
}