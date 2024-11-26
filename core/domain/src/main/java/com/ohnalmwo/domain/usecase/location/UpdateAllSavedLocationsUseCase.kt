package com.ohnalmwo.domain.usecase.location

import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.LocationInfo
import javax.inject.Inject

class UpdateAllSavedLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(locations: List<LocationInfo>) {
        locationRepository.updateAllLocations(locations = locations)
    }
}