package com.example.tfg.core.presentation.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.navigation.AppScreens

@Composable
fun ProductCard(modifier: Modifier, product: Product, onProductClick:(Int) ->Unit){
    FloatingActionButton(

        modifier = Modifier
            .size(width = 150.dp, height = 150.dp),

        onClick = {onProductClick(product.idProduct)}

    ) {

        Box(modifier = Modifier.fillMaxSize()
            .background(Color.White)
        ) {

//            Box(
//                modifier = Modifier.size(30.dp)
//                    .align(Alignment.TopEnd)
//                    .zIndex(1f)
//                    .padding(4.dp)
//
//            ) {
//                Icon(
//                    //TODO:hacerlo clickable
//                    modifier = Modifier.fillMaxSize(),
//                    painter = painterResource(id = R.drawable.favs_black),
//                    contentDescription = "heart_black",
//                    tint = Color(0xFFFF5290)
//                )
//
//            }

            Box(

            ) {
                //la imagen se cargará de forma asíncrona, viene de la api
                AsyncImage(
                    model= ImageRequest.Builder(LocalContext.current)
                        .data(product.imagen)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.home_pink),
                    contentDescription = stringResource(id=R.string.app_name),
                    modifier = Modifier.fillMaxSize()
                )
                Log.i("pic","${product.imagen}")
            }

            Row(modifier=Modifier.align(Alignment.BottomCenter)) {
                Text(
                    text = product.nombre,
                    color = Color.Black,
                )
            }

        }
    }
}



