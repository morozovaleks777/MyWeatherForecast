package com.example.myweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.example.myweatherforecast.data.WeatherDao
import com.example.myweatherforecast.data.WeatherDatabase
import com.example.myweatherforecast.network.WeatherApi
import com.example.myweatherforecast.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn((SingletonComponent::class))
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase):WeatherDao
    = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext
                           context: Context
    ):WeatherDatabase =
        Room.databaseBuilder(
            context,WeatherDatabase::class.java,
            "weather_database"
        ).fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideOpenWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

            .build().create(WeatherApi::class.java)
    }

}