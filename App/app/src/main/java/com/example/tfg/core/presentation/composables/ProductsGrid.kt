package com.example.tfg.core.presentation.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.models.Listado
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.ListByIdVM
import com.example.tfg.viewmodels.MainListVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProductsGrid (modifier: Modifier, onProductClick:(Int)->Unit){

    val vm=MainListVM()
    //TODO:poner a true
    val isLoading:Boolean by vm.isLoading.observeAsState(initial = false)
    val products:List<Product> by vm.listadoProductos.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        vm.getListadoProductos()
    }

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

                    ProductCard(modifier, product, onProductClick)

                }
            }
        )

    }
}

@Composable
fun ProductsGrid (modifier: Modifier, onProductClick:(Int)->Unit, idUsuario:Int){

    val vm=ListByIdVM()
    //TODO:poner a true
    val isLoading:Boolean by vm.isLoading.observeAsState(initial = false)
    val products:List<Product> by vm.listadoProductos.observeAsState(initial = emptyList())
    LaunchedEffect(idUsuario) {
        vm.getListadoProductosByUser(idUsuario)
    }

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

                    ProductCard(modifier, product, onProductClick)

                }
            }
        )
    }
}

