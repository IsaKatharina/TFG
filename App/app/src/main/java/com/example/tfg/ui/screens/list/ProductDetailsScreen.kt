package com.example.tfg.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.buttons.HeartButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProductDetailsScreen(navController: NavController, idProduct: Int) {



    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
        ) {
            BackButton(navController = navController,
                modifier=Modifier)
        }

        Row (
            modifier = Modifier.align(Alignment.End)
        ){
            HeartButton(modifier = Modifier, navController)
        }

        //Imagen del producto
        Box (
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,

        ) {

            //la imagen se cargará de forma asíncrona, viene de la api
            AsyncImage(
                model= ImageRequest.Builder(LocalContext.current)
                    .data(product.body)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.home_pink),
                contentDescription = stringResource(id=R.string.app_name),
                modifier = Modifier.fillMaxSize()
            )
        }

        //nombre del producto
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(product.title,
                fontWeight = FontWeight.Bold, fontSize = 26.sp)

             Text("Nombre del producto original")

        }

        //descripcion del perfil que lo subio
        Row (
            modifier = Modifier.height(65.dp)


        ) {

            ProfileCircle(navController)

            Text("Nombre de la persona que subió el post", fontWeight = FontWeight.Bold)
        }
        BottomBar(navController)
    }

}

@Preview(showSystemUi = true)
@Composable
fun PrProductDetailsScreenScreen(){
    TFGTheme {
        ProductDetailsScreen(navController = rememberNavController(), idProduct = 1)
    }
}