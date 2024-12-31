package com.ohnalmwo.network.datasource.notification

import com.ohnalmwo.network.api.LocationAPI
import com.ohnalmwo.network.api.NotificationAPI
import com.ohnalmwo.network.dto.LocationResponse
import com.ohnalmwo.network.util.ApiHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationDataSourceImpl @Inject constructor(
    private val notificationAPI: NotificationAPI
) : NotificationDataSource {
    override suspend fun saveDeviceToken(deviceToken: String, alarmTime: String): Flow<Unit> = flow {
        ApiHandler<Unit>().request {
            notificationAPI.saveDeviceToken(deviceToken = deviceToken, alarmTime = alarmTime)
        }
    }
}