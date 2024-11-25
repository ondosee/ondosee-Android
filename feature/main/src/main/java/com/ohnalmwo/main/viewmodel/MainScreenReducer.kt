package com.ohnalmwo.main.viewmodel

import androidx.compose.runtime.Immutable
import com.ohnalmwo.common.base.Reducer
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.Weather

class MainScreenReducer : Reducer<MainScreenReducer.MainState, MainScreenReducer.MainEvent, MainScreenReducer.MainEffect> {

    @Immutable
    sealed class MainEvent : Reducer.ViewEvent {
        data class GetWeatherSignificant(val isLoading: Boolean, val significant: Weather) : MainEvent()
        data class GetSavedLocations(val isLoading: Boolean, val localLocations: List<LocationInfo>) : MainEvent()
    }

    @Immutable
    sealed class MainEffect : Reducer.ViewEffect {
        data object NavigateToLocation : MainEffect()
    }

    @Immutable
    data class MainState(
        val isLoading: Boolean,
        val significant: Weather,
        val localLocations: List<LocationInfo>
    ) : Reducer.ViewState {
        companion object {
            fun initial() = MainState(
                isLoading = true,
                significant = Weather.default(),
                localLocations = emptyList()
            )
        }
    }

    override fun reduce(
        previousState: MainState,
        event: MainEvent
    ): Pair<MainState, MainEffect?> =
        when (event) {
            is MainEvent.GetWeatherSignificant -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    significant = event.significant
                ) to null
            }
            is MainEvent.GetSavedLocations -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    localLocations = event.localLocations
                ) to null
            }
        }
}