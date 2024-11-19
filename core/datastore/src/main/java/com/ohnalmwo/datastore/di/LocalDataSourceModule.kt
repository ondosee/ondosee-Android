package com.ohnalmwo.datastore.di

import com.ohnalmwo.datastore.datasource.LocationsDataSource
import com.ohnalmwo.datastore.datasource.LocationsDataSourceImpl
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
}