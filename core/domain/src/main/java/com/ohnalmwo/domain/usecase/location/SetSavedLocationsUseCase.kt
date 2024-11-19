package com.ohnalmwo.domain.usecase.location

import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.LocationInfo
import javax.inject.Inject

class SetSavedLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(location: LocationInfo) {
        locationRepository.setLocations(location = location)
    }
}