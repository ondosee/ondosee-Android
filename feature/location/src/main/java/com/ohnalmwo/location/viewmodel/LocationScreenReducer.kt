package com.ohnalmwo.location.viewmodel

import androidx.compose.runtime.Immutable
import com.ohnalmwo.common.base.Reducer
import com.ohnalmwo.model.Location
import com.ohnalmwo.model.LocationInfo

class LocationScreenReducer : Reducer<LocationScreenReducer.LocationState, LocationScreenReducer.LocationEvent, LocationScreenReducer.LocationEffect> {

    @Immutable
    sealed class LocationEvent : Reducer.ViewEvent {
        data class GetLocationCoordinate(val isLoading: Boolean, val locations: Location) : LocationEvent()
        data class GetSavedLocations(val isLoading: Boolean, val localLocations: List<LocationInfo>) : LocationEvent()
        data object SetSavedLocations : LocationEvent()
        data class OnSearchValueChange(val search: String) : LocationEvent()
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
        val locations: Location,
        val localLocations: List<LocationInfo>,
        val search: String
    ) : Reducer.ViewState {
        companion object {
            fun initial() = LocationState(
                isLoading = true,
                locations = Location.default(),
                localLocations = emptyList(),
                search = ""
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
                    locations = event.locations
                ) to null
            }
            is LocationEvent.GetSavedLocations -> {
                previousState.copy(
                    isLoading = event.isLoading,
                    localLocations = event.localLocations
                ) to null
            }
            is LocationEvent.SetSavedLocations -> {
                previousState to LocationEffect.NavigateToBack
            }
            is LocationEvent.OnSearchValueChange -> {
                previousState.copy(
                    search = event.search
                ) to null
            }
        }
}