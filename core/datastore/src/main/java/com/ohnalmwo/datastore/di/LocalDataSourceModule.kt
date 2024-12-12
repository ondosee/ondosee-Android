package com.ohnalmwo.datastore.di

import com.ohnalmwo.datastore.datasource.location.LocationsDataSource
import com.ohnalmwo.datastore.datasource.location.LocationsDataSourceImpl
import com.ohnalmwo.datastore.datasource.setting.SettingDataSource
import com.ohnalmwo.datastore.datasource.setting.SettingDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocationsDataSource(
        locationsDataSourceImpl: LocationsDataSourceImpl
    ): LocationsDataSource

    @Binds
    @Singleton
    abstract fun bindSettingDataSource(
        settingDataSourceImpl: SettingDataSourceImpl
    ): SettingDataSource
}