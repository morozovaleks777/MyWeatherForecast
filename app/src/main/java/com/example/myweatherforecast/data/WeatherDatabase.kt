package com.example.myweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myweatherforecast.model.Favorite
import com.example.myweatherforecast.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}