package com.example.myweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myweatherforecast.screens.main.MainScreen
import com.example.myweatherforecast.screens.WeatherSplashScreen
import com.example.myweatherforecast.screens.about.AboutScreen
import com.example.myweatherforecast.screens.favorite.FavoriteScreen
import com.example.myweatherforecast.screens.main.MainViewModel
import com.example.myweatherforecast.screens.search.SearchScreen
import com.example.myweatherforecast.screens.settings.SettingsScreen


@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
//        composable(WeatherScreens.MainScreen.name) {
//            val mainViewModel= hiltViewModel<MainViewModel>()
//            MainScreen(navController = navController,mainViewModel)
//        }

        val route = WeatherScreens.MainScreen.name
        composable(
            "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController, mainViewModel = mainViewModel,
                    city = city
                )
            }
        }
                composable(WeatherScreens.SearchScreen.name) {
                    SearchScreen(navController = navController)
                }
        composable(WeatherScreens.AboutScreen.name) {
           AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
            }
        }


