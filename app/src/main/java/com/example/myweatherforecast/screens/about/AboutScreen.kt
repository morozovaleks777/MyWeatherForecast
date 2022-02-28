package com.example.myweatherforecast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.myweatherforecast.R
import com.example.myweatherforecast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController){
    Scaffold(
        topBar = {
           WeatherAppBar(navController = navController,
           title = "About",
               isMainScreen = false,
           icon = Icons.Default.ArrowBack){
               navController.popBackStack()
           }
        }
    ) {
        androidx.compose.material.Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.about_app))
                Text(text = stringResource(id = R.string.api_used))
            }
        }
    }
}