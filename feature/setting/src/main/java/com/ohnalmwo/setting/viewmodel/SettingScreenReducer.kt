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
    }

    @Immutable
    sealed class SettingEffect : Reducer.ViewEffect {}

    @Immutable
    data class SettingState(
        val isLoading: Boolean,
        val isAlarmOn: Switch,
    ) : Reducer.ViewState {
        companion object {
            fun initial() = SettingState(
                isLoading = true,
                isAlarmOn = Switch.OFF,
            )
        }
    }

    override fun reduce(
        previousState: SettingState,
        event: SettingEvent
    ): Pair<SettingState, SettingEffect?> =
        when (event) {
            is SettingEvent.SetAlarmState -> {
                previousState to null
            }

            is SettingEvent.GetAlarmState -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    isAlarmOn = event.isAlarmOn
                ) to null
            }
        }
}