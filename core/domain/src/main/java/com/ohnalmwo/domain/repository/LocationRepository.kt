package com.ohnalmwo.domain.repository

import com.ohnalmwo.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationCoordinate(keyword: String, page: Int): Flow<Location>
}