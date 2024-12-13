package com.example.tfg.core.presentation.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import coil3.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.navigation.AppScreens

@OptIn(ExperimentalGlideComposeApi::class)
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
            Box(modifier = Modifier
            .size(width = 100.dp, height = 100.dp).align(Alignment.Center),
        ) {

            if (product.idProduct == 34) {
                Image(
                    painterResource(id = R.drawable.wha_a_tint),
                    contentDescription = "hardcoded",
                    modifier = Modifier.fillMaxSize()
                )
            } else if (product.idProduct == 35) {
                Image(
                    painterResource(id = R.drawable.benetint),
                    contentDescription = "hardcoded",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                //la imagen se cargará de forma asíncrona, viene de la api
                AsyncImage(
                    model = product.imagen.toUri(),
                    contentDescription = stringResource(id = R.string.app_name),
                    placeholder = (painterResource(R.drawable.home_pink)),
                    modifier = Modifier.fillMaxSize()
                )

            }
        }

                Log.i("pic","${product.imagen}")

            //Spacer(modifier = Modifier.padding(5.dp))

            Row(modifier=Modifier.align(Alignment.BottomCenter)) {
                Text(
                    text = product.nombre,
                    color = Color.Black,
                )
            }

        }
    }
}



