package com.ohnalmwo.setting.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.notification.SaveDeviceTokenUseCase
import com.ohnalmwo.domain.usecase.setting.GetAlarmStateUseCase
import com.ohnalmwo.domain.usecase.setting.SetAlarmStateUseCase
import com.ohnalmwo.domain.usecase.setting.SetAlarmTimeUseCase
import com.ohnalmwo.model.enum.Switch
import com.ohnalmwo.setting.viewmodel.SettingScreenReducer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val setAlarmStateUseCase: SetAlarmStateUseCase,
    private val getAlarmStateUseCase: GetAlarmStateUseCase,
    private val setAlarmTimeUseCase: SetAlarmTimeUseCase
) : BaseViewModel<SettingState, SettingEvent, SettingEffect>(
    initialState = SettingState.initial(),
    reducer = SettingScreenReducer()
) {
    fun setAlarmState(alarmState: Switch) = viewModelScope.launch {
        setAlarmStateUseCase(alarmState = alarmState.value)
        sendEvent(SettingEvent.SetAlarmState)
    }

    fun getAlarmState() = viewModelScope.launch {
        getAlarmStateUseCase()
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = SettingEvent.GetAlarmState(isLoading = true, isAlarmOn = currentState.isAlarmOn))
                    is Result.Success -> sendEvent(event = SettingEvent.GetAlarmState(isLoading = false, isAlarmOn = result.data))
                    is Result.Error -> sendEvent(event = SettingEvent.GetAlarmState(isLoading = false, isAlarmOn = currentState.isAlarmOn))
                }
            }
    }

    fun setAlarmTime(hour: Int, minute: Int, amPm: String) = viewModelScope.launch {
        setAlarmTimeUseCase(formatAlarmTime(hour = hour, minute = minute, amPm = amPm))
        sendEvent(SettingEvent.SetAlarmState)
    }

    private fun formatAlarmTime(hour: Int, minute: Int, amPm: String): String {
        val formattedHour = if (amPm == "PM" && hour < 12) hour + 12
                            else if (amPm == "AM" && hour == 12) 0
                            else hour

        return "%02d:%02d".format(formattedHour, minute)
    }
}