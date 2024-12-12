package com.ohnalmwo.setting.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.Lifecycle
import com.ohnalmwo.common.base.Reducer
import com.ohnalmwo.model.Location
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.Weather
import com.ohnalmwo.model.enum.Switch

class SettingScreenReducer :
    Reducer<SettingScreenReducer.SettingState, SettingScreenReducer.SettingEvent, SettingScreenReducer.SettingEffect> {

    @Immutable
    sealed class SettingEvent : Reducer.ViewEvent {
        data object SetAlarmState : SettingEvent()
        data class GetAlarmState(val isLoading: Boolean, val isAlarmOn: Switch) : SettingEvent()
        data class OnChangeAlarmState(val alarmState: Boolean) : SettingEvent()
    }

    @Immutable
    sealed class SettingEffect : Reducer.ViewEffect {
        data object NavigateToBack : SettingEffect()
    }

    @Immutable
    data class SettingState(
        val isLoading: Boolean,
        val isAlarmOn: Switch,
        val alarmState: Boolean
    ) : Reducer.ViewState {
        companion object {
            fun initial() = SettingState(
                isLoading = true,
                isAlarmOn = Switch.OFF,
                alarmState = false
            )
        }
    }

    override fun reduce(
        previousState: SettingState,
        event: SettingEvent
    ): Pair<SettingState, SettingEffect?> =
        when (event) {
            is SettingEvent.SetAlarmState -> {
                previousState to SettingEffect.NavigateToBack
            }

            is SettingEvent.GetAlarmState -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    isAlarmOn = event.isAlarmOn,
                    alarmState = event.isAlarmOn == Switch.ON
                ) to null
            }

            is SettingEvent.OnChangeAlarmState -> {
                previousState.copy(
                    alarmState = event.alarmState
                ) to null
            }
        }
}