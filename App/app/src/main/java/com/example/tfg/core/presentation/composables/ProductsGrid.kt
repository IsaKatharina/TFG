package com.example.tfg.core.presentation.composables

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.dal.connectivity.ConnectivityObserver
import com.example.tfg.dal.connectivity.NetworkConnectivityObserver
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.MainListVM
import com.example.tfg.viewmodels.ProfileVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ProductsGrid(modifier: Modifier, idUsuario:Int, onEditProductClick:(Int)->Unit){

    val vm= ProfileVM()
    //TODO:poner a true
    val isLoading:Boolean by vm.isLoading.observeAsState(initial = true)
    val products:List<Product> by vm.listadoProductosPorUsuario.observeAsState(initial = emptyList())

    var context= LocalContext.current
    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Available)

    //chequea si hay conexión, si no hay, no se puede seguir con la app.
    if (status.name!="Available") {

        Log.i("sos", "$status.name")

        //TODO:poner imagen de que no hay internet
        Toast.makeText(
            LocalContext.current, // Obtenemos el contexto actual
            "No hay conexión a internet",
            Toast.LENGTH_SHORT
        ).show()

        Column {
            Box(
                modifier = modifier.fillMaxSize(),
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

        LaunchedEffect(idUsuario) {
            vm.getListadoProductosPorUsuario(idUsuario)
        }

        if (isLoading) {

            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center),
                    color = Color(0xFFFF5290)
                )
            }
        } else {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                verticalItemSpacing = 10.dp,
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                content = {
                    items(products) { product ->

                        EditProductCard(product, onEditProductClick)

                    }
                }
            )
        }
    }
}

