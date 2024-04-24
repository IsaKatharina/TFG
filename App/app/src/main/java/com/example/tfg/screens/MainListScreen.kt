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
import com.example.tfg.screens.composables.Search
import com.example.tfg.ui.theme.TFGTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListScreen() {
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
           BottomAppBar(
               containerColor = Color(0xFFFFD9E1),
               contentColor = Color(0xFFFF5290),
           ) {
               Text(
                   modifier = Modifier
                       .fillMaxWidth(),
                   textAlign = TextAlign.Center,
                   text = "Bottom app bar",
               )
           }
       }
   ) {
           innerPadding ->
       Column(
           modifier = Modifier
               .padding(innerPadding),
           verticalArrangement = Arrangement.spacedBy(16.dp),
       ) {
           Text(
               modifier = Modifier.padding(8.dp),
               text =
               """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button  times.
                """.trimIndent(),
           )
       }
   }
}

@Preview
@Composable
fun MainListScreenPr(){
    TFGTheme {
        MainListScreen()
    }

}