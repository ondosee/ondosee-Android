package com.ohnalmwo.datastore.datasource.setting

import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow

interface SettingDataSource {
    suspend fun setAlarmState(alarmState: String)

    fun getAlarmState(): Flow<Switch>

    suspend fun setAlarmTime(alarmTime: String)
}