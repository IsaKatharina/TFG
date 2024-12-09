package com.example.tfg.ui.screens.list

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProductCard
import com.example.tfg.core.presentation.composables.ProductsGrid
import com.example.tfg.dal.connectivity.ConnectivityObserver
import com.example.tfg.dal.connectivity.NetworkConnectivityObserver
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.MainListVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListScreen(navController: NavController) {

    var context= LocalContext.current
    var productoVacio= Product()

    val vm=MainListVM()

    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Available)
    val searchText:String by vm.searchText.observeAsState(initial = "")
    val isLoading:Boolean by vm.isLoading.observeAsState(initial = true)
    val products:List<Product> by vm.listadoProductos.observeAsState(initial=emptyList())
    val productsInit:List<Product> by vm.listadoProductosInicial.observeAsState(initial = emptyList())


    LaunchedEffect(Unit) {
        vm.getListadoProductos()

    }

    //creamos una lambda para navegar a los detalles de un producto
    val navigateToDetails:(Int) ->Unit= {idProduct ->
        navController.navigate(AppScreens.ProductDetailsScreen.route+"/$idProduct")

        Log.i("nav", "navegando a la página de detalles del producto $idProduct")
    }

    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {

        Row (
            modifier = Modifier.padding(10.dp,20.dp, 0.dp, 20.dp)

        ) { Text("New arrivals!", fontWeight = FontWeight.Bold, fontSize = 50.sp, color = Color(0xFFFF5290)) }

        Column (modifier=Modifier.fillMaxSize()
            .weight(1f)
            .padding(5.dp)
        ) {
            //chequea si hay conexión, si no hay, no se puede seguir con la app.
            if (status.name!="Available") {

                Log.i("sos", "$status.name")

//                //TODO:poner imagen de que no hay internet
//                Toast.makeText(
//                    LocalContext.current, // Obtenemos el contexto actual
//                    "No hay conexión a internet",
//                    Toast.LENGTH_SHORT
//                ).show()

                Column {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painterResource(id = R.drawable.nowifi),
                            contentDescription = "no wifi available",
                            tint = Color(0xFFFF5290),
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

            }else {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            if (it.isNotEmpty()){
                            vm.searchProduct(it,productsInit) }
                            else {vm.restartProductslist(productsInit) } },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 15.dp, 15.dp, 15.dp),
                        singleLine = true,
                        maxLines = 1,
                        placeholder = { Text(text = "Search...")}


                    )


                ProductsGrid(modifier = Modifier, isLoading, products, onProductClick = navigateToDetails)

            }
        }

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBar(navController)
        }
    }
}

@Composable
fun ProductsGrid (modifier: Modifier, isLoading:Boolean, products: List<Product>, onProductClick:(Int)->Unit){

        if (isLoading) {

            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color(0xFFFF5290))

            }
        } else {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                verticalItemSpacing = 10.dp,
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                content = {
                    items(products) { product ->

                        Log.i("sos", "pinta el ${product.nombre}")

                        ProductCard(modifier, product, onProductClick)

                    }
                }
            )
        }

}


