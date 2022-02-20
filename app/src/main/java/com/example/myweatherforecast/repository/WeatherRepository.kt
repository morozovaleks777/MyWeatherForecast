package com.example.myweatherforecast.repository

import android.util.Log
import com.example.myweatherforecast.data.DataOrException
import com.example.myweatherforecast.model.Weather
import com.example.myweatherforecast.model.WeatherObject
import com.example.myweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi) {
    suspend fun getWeather(cityQuery:String):DataOrException<Weather,Boolean,Exception>{
val response=try {
    api.getWeather(query = cityQuery)

}catch (e:Exception){
    Log.d("Test", "getWeather error:$e ")
return DataOrException(e=e)
}

return DataOrException(data = response)

    }

}