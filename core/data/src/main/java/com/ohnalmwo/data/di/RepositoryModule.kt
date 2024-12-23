package com.ohnalmwo.data.di

import com.ohnalmwo.data.repository.LocationRepositoryImpl
import com.ohnalmwo.data.repository.MainRepositoryImpl
import com.ohnalmwo.data.repository.SettingRepositoryImpl
import com.ohnalmwo.data.repository.WeatherRepositoryImpl
import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.domain.repository.MainRepository
import com.ohnalmwo.domain.repository.SettingRepository
import com.ohnalmwo.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    @Singleton
    abstract fun bindSettingRepository(
        settingRepositoryImpl: SettingRepositoryImpl
    ): SettingRepository

    @Binds
    @Singleton
    abstract fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}