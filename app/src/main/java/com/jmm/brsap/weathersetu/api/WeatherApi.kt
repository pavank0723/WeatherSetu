package com.jmm.brsap.weathersetu.api

import com.jmm.brsap.weathersetu.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey:String,
        @Query("q") query: String,
        @Query("api") api:String
    ):ApiResponse

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("key") apiKey:String,
        @Query("q") query: String,
        @Query("days") days:Int,
        @Query("aqi") aqi:String,
        @Query("alerts") alerts:String
    ):ApiResponse

    @GET("search.json")
    suspend fun getSearchWeather(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ):ApiResponse
}