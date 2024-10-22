package com.example.tfg.ui.screens.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProductsGrid
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.MainListVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MainListScreen(navController: NavController) {

    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        BackButton(navController = navController,
            modifier=Modifier.align(Alignment.Start))


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

        ) { Text("New arrivals!", fontWeight = FontWeight.Bold) }

        Column (modifier=Modifier.fillMaxSize()
            .weight(1f)
            .padding(15.dp)
        ) {
            ProductsGrid(modifier = Modifier, navController)
        }

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBar(navController)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PCMainListScreen(){
    TFGTheme {
        MainListScreen(
            navController = rememberNavController())
    }
}