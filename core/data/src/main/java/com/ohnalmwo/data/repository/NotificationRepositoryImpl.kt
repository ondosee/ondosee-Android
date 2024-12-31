package com.ohnalmwo.data.repository

import com.ohnalmwo.domain.repository.NotificationRepository
import com.ohnalmwo.network.datasource.notification.NotificationDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDataSource: NotificationDataSource
) : NotificationRepository {
    override suspend fun saveDeviceToken(deviceToken: String, alarmTime: String): Flow<Unit> {
        return notificationDataSource.saveDeviceToken(deviceToken = deviceToken, alarmTime = alarmTime)
    }
}