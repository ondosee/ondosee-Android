package com.ohnalmwo.location.viewmodel

import androidx.compose.runtime.Immutable
import com.ohnalmwo.common.base.Reducer
import com.ohnalmwo.model.Location

class LocationScreenReducer : Reducer<LocationScreenReducer.LocationState, LocationScreenReducer.LocationEvent, LocationScreenReducer.LocationEffect> {

    @Immutable
    sealed class LocationEvent : Reducer.ViewEvent {
        data class GetLocationCoordinate(val isLoading: Boolean, val location: Location) : LocationEvent()
    }

    @Immutable
    sealed class LocationEffect : Reducer.ViewEffect {
        data object NavigateToBack : LocationEffect()
        data object NavigateToAddLocation : LocationEffect()
        data object NavigateToLocationManagement : LocationEffect()
    }

    @Immutable
    data class LocationState(
        val isLoading: Boolean,
        val location: Location
    ) : Reducer.ViewState {
        companion object {
            fun initial() = LocationState(
                isLoading = true,
                location = Location.default()
            )
        }
    }

    override fun reduce(
        previousState: LocationState,
        event: LocationEvent
    ): Pair<LocationState, LocationEffect?> =
        when (event) {
            is LocationEvent.GetLocationCoordinate -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    location = event.location
                ) to null
            }
        }
}