package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.ui.theme.Typography
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.example.tfg.ui.screens.login.StartScreen

@Composable
fun HeaderImagen(modifier: Modifier, navController: NavController) {

    Row(
        modifier=Modifier.fillMaxWidth()
            .padding(45.dp),
        horizontalArrangement = Arrangement.SpaceAround
        
        ) {
        Text(
            text = "Best Dupe ",
            color = Color(0xFFFF5290),
            //TODO:cambiar a titleLarge
            //style = Typography.titleLarge
            style = Typography.titleSmall
        )

        Icon(painter = painterResource(id = R.drawable.home_pink),
            contentDescription = "home_pink",
            tint= Color(0xFFFF5290),
            modifier = Modifier.size(35.dp))

    }

}

@Preview
@Composable
fun PCHeader(){
    TFGTheme {
       HeaderImagen(modifier = Modifier, navController = rememberNavController())
    }
}


