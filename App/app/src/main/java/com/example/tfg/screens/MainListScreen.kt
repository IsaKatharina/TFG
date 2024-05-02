package com.example.tfg.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.screens.composables.BottomBar
import com.example.tfg.screens.composables.ProductCard
import com.example.tfg.screens.composables.Search
import com.example.tfg.ui.theme.TFGTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListScreen(navController: NavController) {
   Scaffold (
       topBar = {
           //poner la barra de búsqueda
           Search()
           Spacer(modifier = Modifier.padding(5.dp))
           TopAppBar(

               colors = topAppBarColors(
                   titleContentColor = Color(0xFF000000),
               ),
               title = {
                   Text("New Arrivals")
               },

           )

       },   bottomBar = {
           BottomBar(navController)
       }
   ) {
           innerPadding ->
       Column(
           modifier = Modifier
               .padding(innerPadding),
           verticalArrangement = Arrangement.spacedBy(16.dp),
       ) {
          ProductCard(modifier=Modifier)
       }
   }
}

