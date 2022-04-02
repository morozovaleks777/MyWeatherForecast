package com.example.myweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherforecast.data.DataOrException
import com.example.myweatherforecast.model.Weather
import com.example.myweatherforecast.model.WeatherObject
import com.example.myweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: WeatherRepository
):ViewModel() {
//  val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
//    mutableStateOf(DataOrException(null, true, Exception("")))
//
//
//  init {
//    loadWeather()
//  }
//
//  private fun loadWeather() {
//   // getWeather("London,uk")
//    getWeather("London")
//  }
//
//  private fun getWeather(city: String) {
//    viewModelScope.launch {
//      if (city.isEmpty()) return@launch
//        data.value.loading=true
//      data.value=repository.getWeather(cityQuery = city)
//      if(data.value.data.toString().isNotEmpty()){ data.value.loading=false}
//    }
//    Log.d("Test", "getWeather: ${data.value.data.toString()}")
//
//  }
suspend fun getWeatherData(city: String, units: String)
        : DataOrException<Weather, Boolean, Exception> {
  return repository.getWeather(cityQuery = city, units = units)

}
  }


