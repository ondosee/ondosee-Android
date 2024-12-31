package com.ohnalmwo.network.api

import com.ohnalmwo.network.dto.LocationResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NotificationAPI {
    @POST("notification")
    suspend fun saveDeviceToken(
        @Query("deviceToken") deviceToken: String,
        @Query("alarmTime") alarmTime: String,
    )
}