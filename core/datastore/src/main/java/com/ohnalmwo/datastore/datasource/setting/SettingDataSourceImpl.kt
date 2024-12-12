package com.ohnalmwo.datastore.datasource.setting

import android.util.Log
import androidx.datastore.core.DataStore
import com.ohnalmwo.datastore.Locations
import com.ohnalmwo.datastore.SettingInfo
import com.ohnalmwo.datastore.mapper.toData
import com.ohnalmwo.datastore.mapper.toDomain
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingDataSourceImpl @Inject constructor (
    private val settingInfo: DataStore<SettingInfo>
) : SettingDataSource {
    override suspend fun setAlarmState(alarmState: String) {
        settingInfo.updateData {
            it.toBuilder()
                .setAlarm(alarmState)
                .build()
        }
    }

    override fun getAlarmState(): Flow<Switch> =
        settingInfo.data.map {
            when (it.alarm) {
                Switch.ON.value -> Switch.ON
                Switch.OFF.value -> Switch.OFF
                else -> Switch.OFF
            }
        }
}