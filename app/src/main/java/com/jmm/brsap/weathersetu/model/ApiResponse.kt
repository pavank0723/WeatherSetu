package com.jmm.brsap.weathersetu.model

import com.example.weatherapplication.models.Current
import com.example.weatherapplication.models.Forecast
import com.example.weatherapplication.models.Location

data class ApiResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
