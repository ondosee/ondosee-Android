package com.ohnalmwo.network.datasource.notification

import com.ohnalmwo.network.dto.LocationResponse
import kotlinx.coroutines.flow.Flow

interface NotificationDataSource {
    suspend fun saveDeviceToken(deviceToken: String, alarmTime: String): Flow<Unit>
}