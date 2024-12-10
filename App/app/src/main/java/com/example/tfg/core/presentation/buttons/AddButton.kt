package com.example.tfg.core.presentation.buttons

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
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
fun AddButton(navController: NavController){
    //creamos una variable que recuerde el estado del botón.
    var buttonColor = remember { mutableStateOf(Color.Black) }

        Log.i("nav", "le he dado al botón de añadir producto")
        //cuando le damos al boton, tiene que navegar hasta Home.
        Button(onClick = {navController.navigate(AppScreens.NewProductScreen.route)
                         buttonColor.value=Color(0xFFFF5290)
                         },
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor=buttonColor.value
           )
        ) {
            Icon(painter = painterResource(id = R.drawable.add_black), contentDescription = "home_black")
            Text(text = "Add", color = buttonColor.value)

        }


}