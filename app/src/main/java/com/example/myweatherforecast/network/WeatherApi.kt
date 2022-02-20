package com.example.myweatherforecast.network

import com.example.myweatherforecast.model.Weather
import com.example.myweatherforecast.model.WeatherObject
import com.example.myweatherforecast.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
//http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}
  @GET(value = "data/2.5/forecast/daily?")
    suspend fun getWeather(
       @Query(value = "q") query:String,
       @Query(value = "units") units:String="imperial",
       @Query(value = "appid") appid:String=API_KEY

    ):Weather
}