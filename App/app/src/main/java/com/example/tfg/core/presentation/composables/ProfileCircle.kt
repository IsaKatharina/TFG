package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.ui.screens.login.NewUserScreen
import com.example.tfg.ui.theme.TFGTheme

@Composable

fun ProfileCircle (navController: NavController) {

    Box(modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        ) {
            drawCircle(
                color = Color(0xFFFF5290),
                center = center,
                radius = size.minDimension / 2,
                style = Stroke(width = 2.dp.toPx()), // Ancho del borde
            )
        }

        Image( modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.home_pink),
            contentDescription = "placeholderImage"
        )



    }

}

@Preview
@Composable
fun PCircle(){
    TFGTheme {
        ProfileCircle(navController = rememberNavController())
    }
}