package com.example.myweatherforecast.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherforecast.model.Favorite
import com.example.myweatherforecast.repository.WeatherDbRepository
import com.example.myweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged()
                .collect {
                    listOfFavs ->
                    if(listOfFavs.isNullOrEmpty()){
                        Log.d("Test", ": is empty ")
                    }else{
                        _favList.value = listOfFavs
                        Log.d("Test", ": ${favList.value} ")
                    }
                }

        }
    }

    fun insertFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.deleteFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite)=viewModelScope.launch {
        repository.updateFavorite(favorite)
    }
    fun deleteAllFavorite()=viewModelScope.launch {
        repository.deleteAllFavorites()

    }
    fun getFavorite()=viewModelScope.launch {
        repository.getFavorites()
    }


}