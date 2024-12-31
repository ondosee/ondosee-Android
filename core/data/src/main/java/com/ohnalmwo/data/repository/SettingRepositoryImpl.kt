package com.ohnalmwo.data.repository

import com.ohnalmwo.datastore.datasource.setting.SettingDataSource
import com.ohnalmwo.domain.repository.SettingRepository
import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val localSettingDataSource: SettingDataSource
) : SettingRepository {
    override suspend fun setAlarmState(alarmState: String) {
        localSettingDataSource.setAlarmState(alarmState = alarmState)
    }

    override fun getAlarmState(): Flow<Switch> {
        return localSettingDataSource.getAlarmState()
    }

    override suspend fun setAlarmTime(alarmTime: String) {
        localSettingDataSource.setAlarmTime(alarmTime = alarmTime)
    }

    override fun getAlarmTime(): Flow<String> {
        return localSettingDataSource.getAlarmTime()
    }
}