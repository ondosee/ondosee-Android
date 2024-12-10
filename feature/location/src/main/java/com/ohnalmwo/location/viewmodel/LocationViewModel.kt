package com.ohnalmwo.location.viewmodel

import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetLocationCoordinateUseCase
import com.ohnalmwo.domain.usecase.location.GetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.location.RemoveSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.location.SetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.location.UpdateAllSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.weather.GetWeatherSignificantUseCase
import com.ohnalmwo.location.util.toCoordinates
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationCoordinateUseCase: GetLocationCoordinateUseCase,
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val setSavedLocationsUseCase: SetSavedLocationsUseCase,
    private val updateAllSavedLocationsUseCase: UpdateAllSavedLocationsUseCase,
    private val removeSavedLocationsUseCase: RemoveSavedLocationsUseCase,
    private val getWeatherSignificantUseCase: GetWeatherSignificantUseCase
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
                    is Result.Success -> {
                        sendEvent(event = LocationEvent.GetSavedLocations(isLoading = false, localLocations = result.data))
                        getMultipleWeatherSignificant(result.data.toCoordinates())
                    }
                    is Result.Error -> sendEvent(event = LocationEvent.GetSavedLocations(isLoading = false, localLocations = currentState.localLocations))
                }
            }
    }

    fun setSavedLocations(location: LocationInfo) = viewModelScope.launch {
        setSavedLocationsUseCase(location = location)
        sendEvent(event = LocationEvent.SetSavedLocations)
    }

    fun updateAllSavedLocations(locations: List<LocationInfo>) = viewModelScope.launch {
        updateAllSavedLocationsUseCase(locations = locations)
    }

    fun removeSavedLocations(index: Int?) = viewModelScope.launch {
        removeSavedLocationsUseCase(index = index)
    }

    fun getMultipleWeatherSignificant(coordinates: List<Pair<Double, Double>>) = viewModelScope.launch {
        val deferredResults = coordinates.map { (x, y) ->
            async {
                getWeatherSignificantUseCase(x = x, y = y)
                    .asResult()
                    .first()
            }
        }

        val results = deferredResults.awaitAll()

        when {
            results.all { it is Result.Loading } -> sendEvent(event = LocationEvent.GetMultipleWeatherSignificant(isLoading = true, locationsWeatherSignificant = currentState.locationsWeatherSignificant))
            results.any { it is Result.Error } -> sendEvent(event = LocationEvent.GetMultipleWeatherSignificant(isLoading = false, locationsWeatherSignificant = currentState.locationsWeatherSignificant))
            else -> sendEvent(event = LocationEvent.GetMultipleWeatherSignificant(isLoading = false, locationsWeatherSignificant = results.filterIsInstance<Result.Success<Weather>>().map { it.data }))
        }
    }
}