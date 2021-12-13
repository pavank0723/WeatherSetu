package com.jmm.brsap.weathersetu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmm.brsap.weathersetu.model.ApiResponse
import com.jmm.brsap.weathersetu.repository.WeatherRepo
import com.jmm.brsap.weathersetu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
):ViewModel() {
//    suspend fun getCurrentWeather(location: String):ApiResponse = weatherRepo.getCurrentWeather(location)

/*
    suspend fun getForecastWeather(location: String):ApiResponse = weatherRepo.getForecastWeather(location)

    suspend fun getSearchWeather(location: String):ApiResponse = weatherRepo.getSearchWeather(location)
*/

    private val _currentWeather = MutableLiveData<Resource<ApiResponse>>()
    val currentWeather: LiveData<Resource<ApiResponse>> = _currentWeather

    fun getCurrentWeather(query: String) {
        viewModelScope.launch {
            weatherRepo
                .getCurrentWeather(query)
                .onStart {
                    _currentWeather.postValue(Resource.Loading(true))
                }
                .catch { exception ->
                    exception.message?.let {
                        _currentWeather.postValue(Resource.Error(it))
                    }
                }
                .collect { response ->
                    _currentWeather.postValue(Resource.Success(response))
                }
        }

    }

    private val _forecastWeather = MutableLiveData<Resource<ApiResponse>>()
    val forecastWeather: LiveData<Resource<ApiResponse>> = _forecastWeather

    fun getForecastWeather(query: String,days:Int) {
        viewModelScope.launch {
            weatherRepo.getForecastWeather(query,days)
                .onStart {
                    _forecastWeather.postValue(Resource.Loading(true))
                }
                .catch { exception ->
                    exception.message?.let {
                        _forecastWeather.postValue(Resource.Error(it))
                    }
                }
                .collect { response ->
                    _forecastWeather.postValue(Resource.Success(response))
                }
        }

    }

    private val _searchWeather = MutableLiveData<Resource<ApiResponse>>()
    val searchWeather: LiveData<Resource<ApiResponse>> = _searchWeather

    fun getSearchWeather(query: String) {
        viewModelScope.launch {
            weatherRepo
                .getCurrentWeather(query)
                .onStart {
                    _searchWeather.postValue(Resource.Loading(true))
                }
                .catch { exception ->
                    exception.message?.let {
                        _searchWeather.postValue(Resource.Error(it))
                    }
                }
                .collect { response ->
                    _searchWeather.postValue(Resource.Success(response))
                }
        }

    }

}