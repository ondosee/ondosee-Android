package com.ohnalmwo.location.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetLocationCoordinateUseCase
import com.ohnalmwo.domain.usecase.location.GetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.location.RemoveSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.location.SetSavedLocationsUseCase
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.model.LocationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationCoordinateUseCase: GetLocationCoordinateUseCase,
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val setSavedLocationsUseCase: SetSavedLocationsUseCase,
    private val removeSavedLocationsUseCase: RemoveSavedLocationsUseCase,
) : BaseViewModel<LocationState, LocationEvent, LocationEffect>(
    initialState = LocationState.initial(),
    reducer = LocationScreenReducer()
) {
    fun getLocationCoordinate(search: String) = viewModelScope.launch {
        getLocationCoordinateUseCase(keyword = search, page = 1)
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = true, locations = currentState.locations))
                    is Result.Success -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = false, locations = result.data))
                    is Result.Error -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = false, locations = currentState.locations))
                }
            }
    }

    fun getSavedLocations() = viewModelScope.launch {
        getSavedLocationsUseCase()
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = LocationEvent.GetSavedLocations(isLoading = true, localLocations = currentState.localLocations))
                    is Result.Success -> { sendEvent(event = LocationEvent.GetSavedLocations(isLoading = false, localLocations = result.data)) }
                    is Result.Error -> { sendEvent(event = LocationEvent.GetSavedLocations(isLoading = false, localLocations = currentState.localLocations)) }
                }
            }
    }

    fun setSavedLocations(location: LocationInfo) = viewModelScope.launch {
        setSavedLocationsUseCase(location = location)
        sendEvent(event = LocationEvent.SetSavedLocations)
    }
}