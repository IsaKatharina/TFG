package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens

@Composable
fun HomeButton(navController: NavController){
   //creamos una variable que recuerde el estado del botón.
    var buttonColor = remember { mutableStateOf(Color.Black) }
    var containerColor = remember { mutableStateOf(Color.Transparent) }

        //cuando le damos al boton, tiene que navegar hasta Home.
        Button(onClick = {navController.navigate(AppScreens.MainListScreen.route)
            buttonColor.value=Color(0xFFFF5290)
           },

            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor.value,
                contentColor=buttonColor.value
            )) {

            Icon(painter = painterResource(id = R.drawable.home_black), contentDescription = "home_black")
           Text(text = "Home", color = buttonColor.value)

        }


}