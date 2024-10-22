package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens

@Composable
fun HomeButton(navController: NavController, modifier: Modifier){
   //creamos una variable que recuerde el estado del botón.
    val botonClickado:Boolean=false

    if (botonClickado) {

        Button(onClick = {}, enabled = false) {

            Icon(painter = painterResource(id = R.drawable.home_pink), contentDescription = "home pink", tint = Color(0xFFFF5290))
            Text(text = "Home", color = Color(0xFFFF5290))

        }


    } else {
        //cuando le damos al boton, tiene que navegar hasta Home.
        Button(onClick = {navController.navigate(AppScreens.MainListScreen.route)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor=Color.Black
            )) {

            Icon(painter = painterResource(id = R.drawable.home_black), contentDescription = "home_black")
            Text(text = "Home", color = Color.Black)

        }
    }

}