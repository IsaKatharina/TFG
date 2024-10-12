package com.example.tfg.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar

@Composable
fun FavsScreen(navController: NavController) {

    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        BackButton(navController = navController,
            modifier= Modifier.align(Alignment.Start))


        //aqui va la top bar
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 1.dp,
                color = Color.DarkGray
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Aquí va la barra de búsqueda")
        }
        Row (
            modifier = Modifier.padding(10.dp,20.dp, 0.dp, 20.dp)

        ) { Text("Your favs!", fontWeight = FontWeight.Bold) }

        //aqui va la lista de productos
        Row (
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ) {
            //TODO:hacer una lazy list de productos. que sea scrollable
        }


        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBar(navController)
        }
    }
}