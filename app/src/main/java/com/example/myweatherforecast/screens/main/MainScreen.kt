package com.example.myweatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweatherforecast.R
import com.example.myweatherforecast.data.DataOrException
import com.example.myweatherforecast.model.Weather
import com.example.myweatherforecast.model.WeatherItem
import com.example.myweatherforecast.navigation.WeatherScreens
import com.example.myweatherforecast.utils.formatDate
import com.example.myweatherforecast.utils.formatDateTime
import com.example.myweatherforecast.utils.formatDecimals
import com.example.myweatherforecast.widgets.WeatherAppBar
import com.example.myweatherforecast.widgets.WeatherStateImage

@Composable
fun MainScreen(city: String?,navController: NavController, mainViewModel: MainViewModel= hiltViewModel()){

    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue =DataOrException(loading = true)){
        value=mainViewModel.getWeatherData(city=city.toString())
    }.value
    if(weatherData.loading==true){
        CircularProgressIndicator()
    }else if (weatherData.data !=null){
        Text(text = "MainScreen ${weatherData.data!!.city.name}")

        MainScaffold(weather = weatherData.data!!, navController = navController)
    }



}

@Composable
fun MainScaffold(weather:Weather,navController: NavController){
Scaffold(topBar = {
    WeatherAppBar(navController = navController, title = weather.city.name +" ,${weather.city.country}",
        onAddActionClicked={
                           navController.navigate(WeatherScreens.SearchScreen.name)
        },

       icon = Icons.Default.ArrowBack ,elevation = 5.dp){
        Log.d("Test", "MainScaffold: onClicked arrow ")
    }
}) {
    MainContent(weather)
    
}

}

@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth())
        {
        Text(text = formatDate(data.list[0].dt))
        Surface(
            modifier = Modifier
                .padding(6.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement =Arrangement.Center ) {
    // image
    WeatherStateImage(imageUrl = imageUrl)
    Text(text = formatDecimals(data.list[0].temp.day)+"°", style = MaterialTheme.typography.h4,
    fontWeight = FontWeight.ExtraBold)
    Text(text = data.list[0].weather[0].main, fontStyle = FontStyle.Italic)

           }
        }
            HumidityWindPressureRow(weather=data.list[0])

            Divider()

            SunsetSunriseRow(weather=data.list[0])

            Text(text = "This week",
            style =MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold)

            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFEEF1EF)
                    ){
                LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp) ){
                  items(items = data.list){
                      item:WeatherItem ->
                      WeatherDetailRow(weather=item)
                  }
                }

            }

    }


}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
        Surface(modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(80.dp), shape = RoundedCornerShape(topEnd = 35.dp, bottomStart = 14.dp),
        elevation = 5.dp, border = BorderStroke(1.dp, color = Color.DarkGray)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

                ) {
                Text(text = formatDate(weather.dt))

                WeatherStateImage(imageUrl = imageUrl)

                Surface(modifier = Modifier.padding(2.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)) {
                    Text(text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp))
                }

//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(text = formatDecimals(weather.temp.max),
//                        color = Color.Red, fontWeight = FontWeight.Bold
//                    )
//                    Text(text = formatDecimals(weather.temp.min),
//                        color = Color.Blue)
//                }

                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Red.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold )){
                        append(formatDecimals(weather.temp.max) +"°")
                    }

                    withStyle(style = SpanStyle(color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold )){
                        append(formatDecimals(weather.temp.min) +"°")
                    }

                })

            }
        }
    }



@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription ="sunrise icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${formatDateTime(weather.sunrise)} ")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription ="sunset icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${formatDateTime(weather.sunset)} ")
        }
    }

}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription ="humidity icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.humidity} %")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription ="wind icon",
                modifier = Modifier.size(20.dp))
            Text(text =" ${weather.speed} mph")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription ="pressure icon",
            modifier = Modifier.size(20.dp))
            Text(text = "${weather.pressure} psi")

        }

    }

}
