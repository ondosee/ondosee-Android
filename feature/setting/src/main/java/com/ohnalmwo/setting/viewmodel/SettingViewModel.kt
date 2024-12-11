package com.ohnalmwo.setting.viewmodel

import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.setting.GetAlarmStateUseCase
import com.ohnalmwo.domain.usecase.setting.SetAlarmStateUseCase
import com.ohnalmwo.model.enum.Switch
import com.ohnalmwo.setting.viewmodel.SettingScreenReducer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val setAlarmStateUseCase: SetAlarmStateUseCase,
    private val getAlarmStateUseCase: GetAlarmStateUseCase
) : BaseViewModel<SettingState, SettingEvent, SettingEffect>(
    initialState = SettingState.initial(),
    reducer = SettingScreenReducer()
) {
    fun setAlarmState(alarmState: Switch) = viewModelScope.launch {
        setAlarmStateUseCase(alarmState = alarmState.value)
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
}