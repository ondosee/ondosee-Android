package com.ohnalmwo.main.viewmodel

import androidx.compose.runtime.Immutable
import com.ohnalmwo.common.base.Reducer
import com.ohnalmwo.model.Weather

class MainScreenReducer : Reducer<MainScreenReducer.MainState, MainScreenReducer.MainEvent, MainScreenReducer.MainEffect> {

    @Immutable
    sealed class MainEvent : Reducer.ViewEvent {
        data class GetWeatherSignificant(val isLoading: Boolean, val significant: Weather) : MainEvent()
    }

    @Immutable
    sealed class MainEffect : Reducer.ViewEffect {
        data object NavigateToLocation : MainEffect()
    }

    @Immutable
    data class MainState(
        val isLoading: Boolean,
        val significant: Weather
    ) : Reducer.ViewState {
        companion object {
            fun initial() = MainState(
                isLoading = true,
                significant = Weather(weathers = emptyList())
            )
        }
    }

    override fun reduce(
        previousState: MainState,
        event: MainEvent
    ): Pair<MainState, MainEffect?> =
        when(event) {
            is MainEvent.GetWeatherSignificant -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    significant = event.significant
                ) to null
            }
        }
}