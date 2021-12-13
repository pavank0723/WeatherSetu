package com.jmm.brsap.weathersetu.model

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>,
    var isSelected: Boolean = false
)