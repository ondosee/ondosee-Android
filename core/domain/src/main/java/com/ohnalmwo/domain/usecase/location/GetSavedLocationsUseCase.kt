package com.ohnalmwo.domain.usecase.location

import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.LocationInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(): Flow<List<LocationInfo>> =
        locationRepository.getLocations()
}