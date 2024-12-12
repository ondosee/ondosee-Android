package com.ohnalmwo.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.ohnalmwo.datastore.Locations
import com.ohnalmwo.datastore.SettingInfo
import com.ohnalmwo.datastore.serializer.LocationsSerializer
import com.ohnalmwo.datastore.serializer.SettingSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideLocationsDataStore(
        @ApplicationContext context: Context,
        locationsSerializer: LocationsSerializer
    ): DataStore<Locations> =
        DataStoreFactory.create(
            serializer = locationsSerializer,
        ) {
            context.dataStoreFile("locations.pb")
        }

    @Provides
    @Singleton
    fun provideSettingDataStore(
        @ApplicationContext context: Context,
        settingSerializer: SettingSerializer
    ): DataStore<SettingInfo> =
        DataStoreFactory.create(
            serializer = settingSerializer,
        ) {
            context.dataStoreFile("settingInfo.pb")
        }
}