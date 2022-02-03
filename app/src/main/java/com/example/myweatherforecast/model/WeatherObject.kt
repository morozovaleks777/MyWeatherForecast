package com.example.myweatherforecast.model

data class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String)