package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.EditButton
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun EditProductCard(product: Product, onEditProductClick:(Int)->Unit){

    FloatingActionButton (

        modifier = Modifier
            .size(width = 150.dp, height = 150.dp),

        onClick = {onEditProductClick(product.idProduct)}

        ) {

        Box(modifier = Modifier.fillMaxSize()
            .background(Color.White)
        ) {


            Box(

            ) {
                //la imagen se cargará de forma asíncrona, viene de la api
                AsyncImage(
                    model= ImageRequest.Builder(LocalContext.current)
                        .data("whatever")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.home_pink),
                    contentDescription = stringResource(id= R.string.app_name),
                    modifier = Modifier.fillMaxSize()
                )
                //Log.i("pic","${product.imagen}")
            }

            Row(modifier= Modifier.align(Alignment.BottomCenter)) {
                Text(
                    text = product.nombre,
                    color = Color.Black,
                )
            }

        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun PRPEditProductCard(){
//    TFGTheme {
//        EditProductCard (product = Product(), onEditProductClick)
//    }
//
//}
