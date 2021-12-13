package com.jmm.brsap.weathersetu.repository

import com.jmm.brsap.weathersetu.api.WeatherApi
import com.jmm.brsap.weathersetu.model.ApiResponse
import com.jmm.brsap.weathersetu.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepo @Inject constructor(private val weatherApi: WeatherApi) {

//    suspend fun getCurrentWeather(query:String):ApiResponse = weatherApi.getCurrentWeather(Constants.API_KEY,query,"no")

//    suspend fun getForecastWeather(query: String):ApiResponse = weatherApi.getForecastWeather(Constants.API_KEY,query,7,"no","no")

//    suspend fun getSearchWeather(query: String):ApiResponse = weatherApi.getSearchWeather(Constants.API_KEY,query)

    suspend fun getCurrentWeather(query: String): Flow<ApiResponse> {
        return flow {
            val response = weatherApi.getCurrentWeather(Constants.API_KEY,query,"no")

            emit(response)
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getForecastWeather(query: String,days:Int): Flow<ApiResponse> {
        return flow {
            val response = weatherApi.getForecastWeather(Constants.API_KEY,query,days,"no","no")

            emit(response)
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getSearchWeather(query: String): Flow<ApiResponse> {
        return flow {
            val response = weatherApi.getSearchWeather(Constants.API_KEY,query)

            emit(response)
        }.flowOn(Dispatchers.IO)

    }

}