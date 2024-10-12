package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens

@Composable
fun HeartButton(modifier: Modifier, navController: NavController) {
    // Creamos una variable que recuerde el estado del botón.
    var botonClickado = remember { mutableStateOf(false) }

    if (botonClickado.value) {
        Button(
            onClick = {
                // Aquí puedes agregar la lógica para agregar el elemento a la lista de favoritos.
                // Por ahora, simplemente cambiamos el estado del botón.
                botonClickado.value = false
            }
        ) {

            Icon(
                    painter = painterResource(id = R.drawable.favs_pink),
                    contentDescription = "heart_pink",
                    tint = Color(0xFFFF5290),
                    modifier=Modifier.size(35.dp)
                )

        }
    } else {
        // Cuando el botón no está clicado.
        Button(
            onClick = {
                //ponemos el estado del botón a positivo
                //ha sido clickado
                botonClickado.value = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            )
        ) {
                Icon(
                    painter = painterResource(id = R.drawable.favs_black),
                    contentDescription = "heart_black",
                    tint = Color(0xFFFF5290),
                    modifier=Modifier.size(35.dp)
                )


        }
    }
}




