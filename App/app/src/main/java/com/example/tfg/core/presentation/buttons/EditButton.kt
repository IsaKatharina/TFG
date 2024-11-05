package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.material3.IconButton
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.navigation.AppScreens

@Composable
fun EditButton(navController: NavController,idProduct: Int) {
    //creamos una variable que recuerde el estado del botón.
    val botonClickado:Boolean=false

    if(!botonClickado) {

        IconButton(onClick = {navController.navigate(AppScreens.EditProductScreen.route +"$idProduct")}) {
            Icon(
                painter= painterResource(id = R.drawable.edit_icon),
                contentDescription = "EditProfile",
                tint = Color.Green,
                modifier= Modifier.size(35.dp)
            )

        }

    }
}