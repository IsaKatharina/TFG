package com.example.tfg.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProductCard
import com.example.tfg.core.presentation.composables.Search
import com.example.tfg.navigation.AppNavigation
import com.example.tfg.ui.screens.login.LoginScreen
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.ui.theme.backgroundLight
import com.example.tfg.viewmodels.LoginVM


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

        //aqui va la lista de productos
        Row (
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ) {
            //TODO:hacer una lazy list de productos
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
        MainListScreen(navController = rememberNavController())
    }
}