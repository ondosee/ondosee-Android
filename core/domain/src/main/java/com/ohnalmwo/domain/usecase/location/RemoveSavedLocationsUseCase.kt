package com.ohnalmwo.domain.usecase.location

import com.ohnalmwo.domain.repository.LocationRepository
import javax.inject.Inject

class RemoveSavedLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(index: Int?) {
        if (index != null) locationRepository.removeLocations(index = index)
        else locationRepository.removeAllLocations()
    }
}