package com.example.myweatherforecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title:String="Title",
    icon: ImageVector?=null,
    isMainScreen:Boolean=true,
    elevation: Dp =0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: ()->Unit={}
){

    val showDialog = remember {
        mutableStateOf(false)
    }
    val showIt = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

//    if (showDialog.value) {
//        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
//    }


    TopAppBar(
    title = {
        Text(text = title, color = MaterialTheme.colors.secondary,
    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
    )
    },
    actions={
        if (isMainScreen){
            IconButton(onClick = {
                onAddActionClicked.invoke()
            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "search icon")

            }
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "More Icon" )

            }
        }else Box {}
    },
    navigationIcon = {
                     if(icon!=null){
                         Icon(imageVector = icon, contentDescription =null,
                         tint = MaterialTheme.colors.onSecondary,
                         modifier = Modifier.clickable {
                             onButtonClicked.invoke()
                         })
                     }
    },
    backgroundColor = Color.Transparent,
    elevation = elevation
)
}