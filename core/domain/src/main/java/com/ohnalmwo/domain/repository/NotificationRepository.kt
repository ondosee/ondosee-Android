package com.ohnalmwo.domain.repository

import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun saveDeviceToken(deviceToken: String, alarmTime: String): Flow<Unit>
}
