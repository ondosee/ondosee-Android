package com.ohnalmwo.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.ohnalmwo.common.base.BaseViewModel
import com.ohnalmwo.common.result.Result
import com.ohnalmwo.common.result.asResult
import com.ohnalmwo.domain.usecase.GetWeatherSignificantUseCase
import com.ohnalmwo.main.viewmodel.MainScreenReducer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherSignificantUseCase: GetWeatherSignificantUseCase
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
}