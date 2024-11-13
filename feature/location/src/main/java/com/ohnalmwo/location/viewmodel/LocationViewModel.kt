package com.ohnalmwo.location.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetLocationCoordinateUseCase
import com.ohnalmwo.location.viewmodel.LocationScreenReducer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationCoordinateUseCase: GetLocationCoordinateUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<LocationState, LocationEvent, LocationEffect>(
    initialState = LocationState.initial(),
    reducer = LocationScreenReducer()
) {
    var search = savedStateHandle.getStateFlow(key = SEARCH, initialValue = "")

    init {
        viewModelScope.launch {
            search
                .debounce(300L)
                .filter { it.isNotEmpty() }
                .flatMapLatest { keyword ->
                    getLocationCoordinateUseCase(keyword = keyword, page = 1)
                        .asResult()
                }
                .collect { result ->
                    when (result) {
                        is Result.Loading -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = true, location = currentState.location))
                        is Result.Success -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = false, location = result.data))
                        is Result.Error -> sendEvent(event = LocationEvent.GetLocationCoordinate(isLoading = false, location = currentState.location))
                    }
                }
        }
    }

    fun onSearchChange(value: String) {
        savedStateHandle[SEARCH] = value
    }
}

private const val SEARCH = "search"