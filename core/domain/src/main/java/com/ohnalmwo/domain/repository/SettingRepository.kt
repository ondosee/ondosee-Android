package com.ohnalmwo.domain.repository

import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun setAlarmState(alarmState: String)

    fun getAlarmState(): Flow<Switch>
}