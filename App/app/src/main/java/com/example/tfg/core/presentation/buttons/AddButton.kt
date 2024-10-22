package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens


@Composable
fun AddButton(navController: NavController, modifier: Modifier){
    //creamos una variable que recuerde el estado del botón.
    val botonClickado:Boolean=false

    if (botonClickado) {

        Button(onClick = {}, enabled = false,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor=Color(0xFFFF5290)
            )) {

            Icon(painter = painterResource(id = R.drawable.add_pink), contentDescription = "add pink", tint = Color(0xFFFF5290))
            Text(text = "Add", color = Color(0xFFFF5290))

        }


    } else {
        //cuando le damos al boton, tiene que navegar hasta Home.
        Button(onClick = {navController.navigate(AppScreens.NewProductScreen.route)},
           colors = ButtonDefaults.buttonColors(
               containerColor = Color.Transparent,
               contentColor=Color.Black
           )
        ) {


            Icon(painter = painterResource(id = R.drawable.add_black), contentDescription = "home_black")
            Text(text = "Add", color = Color.Black)

        }
    }

}