package com.example.tfg.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.core.presentation.buttons.EditProfileButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProductsGrid
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.screens.list.MainListScreen
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProfileScreen(navController: NavController) {

    var idUsuario=1

    //creamos una lambda para navegar a los detalles de un producto
    val navigateToDetails:(Int) ->Unit= {idProduct ->
        navController.navigate(AppScreens.ProductDetailsScreen.route+"/$idProduct")

        Log.i("navEdit", "navegando a la edición del producto $idProduct")
    }

    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {

        //aqui va la top bar
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 1.dp,
                color = Color.DarkGray
            ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(modifier = Modifier.size(50.dp)) {
                ProfileCircle(navController)
            }


            Column (
                modifier = Modifier.padding(10.dp)
            ){
                Text("Nombre del usuario", fontWeight = FontWeight.Bold)

                Text("Información de lo que sea", fontWeight = FontWeight.Thin)
            }

            EditProfileButton (navController)

        }

        Row (
            modifier = Modifier.padding(10.dp,20.dp, 0.dp, 20.dp)

        ) { Text("Your products", fontWeight = FontWeight.Bold) }

        Column (modifier= Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(15.dp)
        ) {
            ProductsGrid(modifier = Modifier, onProductClick = navigateToDetails, idUsuario)
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
fun PRProfileScreen(){
    TFGTheme {
        ProfileScreen(
            navController = rememberNavController()
        )
    }

}