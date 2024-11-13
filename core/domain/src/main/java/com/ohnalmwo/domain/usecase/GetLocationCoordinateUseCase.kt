package com.ohnalmwo.domain.usecase

import com.ohnalmwo.domain.repository.LocationRepository
import com.ohnalmwo.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationCoordinateUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(keyword: String, page: Int): Flow<Location> =
        locationRepository.getLocationCoordinate(keyword = keyword, page = page)
}