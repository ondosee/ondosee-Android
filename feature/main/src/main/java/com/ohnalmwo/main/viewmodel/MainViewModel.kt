package com.ohnalmwo.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.location.GetSavedLocationsUseCase
import com.ohnalmwo.domain.usecase.main.GetTutorialDialogStateUseCase
import com.ohnalmwo.domain.usecase.main.SetTutorialDialogStateUseCase
import com.ohnalmwo.domain.usecase.weather.GetWeatherSignificantUseCase
import com.ohnalmwo.main.viewmodel.MainScreenReducer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherSignificantUseCase: GetWeatherSignificantUseCase,
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val getTutorialDialogStateUseCase: GetTutorialDialogStateUseCase,
    private val setTutorialDialogStateUseCase: SetTutorialDialogStateUseCase
) : BaseViewModel<MainState, MainEvent, MainEffect>(
    initialState = MainState.initial(),
    reducer = MainScreenReducer()
) {
    fun getWeatherSignificant(x: Double, y: Double) = viewModelScope.launch {
        getWeatherSignificantUseCase(x = x, y = y)
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = MainEvent.GetWeatherSignificant(isLoading = true, significant = currentState.significant))
                    is Result.Success -> sendEvent(event = MainEvent.GetWeatherSignificant(isLoading = false, significant = result.data))
                    is Result.Error -> sendEvent(event = MainEvent.GetWeatherSignificant(isLoading = false, significant = currentState.significant))
                }
            }
    }

    fun getSavedLocations() = viewModelScope.launch {
        getSavedLocationsUseCase()
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = MainEvent.GetSavedLocations(isLoading = true, localLocations = currentState.localLocations))
                    is Result.Success -> sendEvent(event = MainEvent.GetSavedLocations(isLoading = false, localLocations = result.data))
                    is Result.Error -> sendEvent(event = MainEvent.GetSavedLocations(isLoading = false, localLocations = currentState.localLocations))
                }
            }
    }

    fun getTutorialDialogState() = viewModelScope.launch {
        getTutorialDialogStateUseCase()
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Loading -> sendEvent(event = MainEvent.GetTutorialDialogState(isLoading = true, openDialog = currentState.openDialog))
                    is Result.Success -> sendEvent(event = MainEvent.GetTutorialDialogState(isLoading = false, openDialog = result.data))
                    is Result.Error -> sendEvent(event = MainEvent.GetTutorialDialogState(isLoading = false, openDialog = currentState.openDialog))
                }
            }
    }

    fun setTutorialDialogState(openDialog: Boolean) = viewModelScope.launch {
        setTutorialDialogStateUseCase(openDialog = openDialog)
        sendEvent(MainEvent.SetTutorialDialogState(openDialog = openDialog))
    }
}