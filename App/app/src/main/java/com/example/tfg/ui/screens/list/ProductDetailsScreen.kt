package com.example.tfg.ui.screens.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.buttons.HeartButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.MainListVM
import com.example.tfg.viewmodels.ProductDetailsVM

@Composable
fun ProductDetailsScreen(navController: NavController, idProduct: Int) {

    val vm=ProductDetailsVM()
    //TODO:poner a true
    val isLoading:Boolean by vm.isLoading.observeAsState(initial = true)

    //buscamos el producto correspondiente
    val product: Product by vm.productFound.observeAsState(initial = Product())

    LaunchedEffect(idProduct) {
        vm.getProduct(idProduct)
    }

    if (isLoading) {

        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color(0xFFFF5290))

        }
    } else {
        //en caso de que el idProducto sea distinto de 0
            if (product.idProduct != 0) {

                Log.i("det", "${product.idProduct}")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                    ) {
                        BackButton(
                            navController = navController,
                            modifier = Modifier
                        )
                    }

                    Row(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        HeartButton(modifier = Modifier, navController)
                    }

                    //Imagen del producto
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center,
                        ) {

                        //la imagen se cargará de forma asíncrona, viene de la api
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(product.imagen)
                                .crossfade(true)
                                .build(),
                            error = painterResource(id = R.drawable.home_pink),
                            contentDescription = stringResource(id = R.string.app_name),
                            modifier = Modifier.fillMaxSize(),

                        )
                    }

                    //nombre del producto
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                                        Text(product.nombre,
                                            fontWeight = FontWeight.Bold, fontSize = 26.sp)

                        Log.i("det","nombre ${product.nombre}")


                    }

                    //descripcion del perfil que lo subio
                    Row(
                        modifier = Modifier.height(65.dp)


                    ) {

                        ProfileCircle(navController)

                        Text("Nombre de la persona que subió el post", fontWeight = FontWeight.Bold)
                    }
                    BottomBar(navController)
                }

                //TODO:ponemos un mensaje de error.
            } else {

                Text("No ha salido bien")
                Log.i("det","no ha salido bien")

            }
        }
    

}

//@Preview(showSystemUi = true)
//@Composable
//fun PrProductDetailsScreenScreen(){
//    TFGTheme {
//        ProductDetailsScreen(navController = rememberNavController(), idProduct = 1)
//    }
//}