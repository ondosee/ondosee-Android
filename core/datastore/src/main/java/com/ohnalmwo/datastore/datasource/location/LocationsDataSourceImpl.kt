package com.ohnalmwo.datastore.datasource.location

import androidx.datastore.core.DataStore
import com.ohnalmwo.datastore.Locations
import com.ohnalmwo.datastore.mapper.toData
import com.ohnalmwo.datastore.mapper.toDomain
import com.ohnalmwo.model.LocationInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationsDataSourceImpl @Inject constructor(
    private val locations: DataStore<Locations>
) : LocationsDataSource {
    override fun getLocations(): Flow<List<LocationInfo>> = locations.data.map { location ->
        location.locationList.map { it.toDomain() }
    }

    override suspend fun setLocations(location: LocationInfo) {
        locations.updateData {
            val builder = it.toBuilder()

            if (builder.locationList.size < 4 && builder.locationList.none { it.toDomain() == location }) {
                builder.addLocation(location.toData())
            }

            builder.build()
        }
    }

    override suspend fun updateAllLocations(locations: List<LocationInfo>) {
        this.locations.updateData {
            it.toBuilder()
                .clearLocation()
                .addAllLocation(locations.map { it.toData() })
                .build()
        }
    }

    override suspend fun removeLocations(index: Int) {
        locations.updateData {
            val builder = it.toBuilder()
            if (index in 0 until builder.locationCount) {
                builder.removeLocation(index)
            }
            builder.build()
        }
    }

    override suspend fun removeAllLocations() {
        locations.updateData {
            it.toBuilder()
                .clearLocation()
                .build()
        }
    }
}