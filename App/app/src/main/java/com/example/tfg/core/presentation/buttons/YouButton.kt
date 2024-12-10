package com.example.tfg.core.presentation.buttons

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme


@Composable
fun YouButton(navController: NavController){
    //creamos una variable que recuerde el estado del botón.
    var buttonColor = remember { mutableStateOf(Color.Black) }

    //cuando le damos al boton, tiene que navegar hasta Home.
        Button(onClick = {navController.navigate(AppScreens.ProfileScreen.route)
            buttonColor.value=Color(0xFFFF5290)
                         },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor=buttonColor.value
            )
        ) { Icon(painter = painterResource(id = R.drawable.person_black), contentDescription = "home_black")
           Text(text = "You", color = buttonColor.value)
        }
}

@Preview
@Composable
fun PrButton(){
    TFGTheme {

        YouButton(navController = rememberNavController())

    }
}