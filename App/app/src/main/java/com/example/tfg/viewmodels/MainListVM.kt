package com.example.tfg.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.http.Query

//vm de la lista principal
class MainListVM: ViewModel() {

    private var _listadoProductos: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    var listadoProductos: LiveData<List<Product>> = _listadoProductos

    private var _listadoProductosInicial: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    var listadoProductosInicial: LiveData<List<Product>> = _listadoProductosInicial

    private var _listadoProductosPorUsuario: MutableLiveData<List<Product>> =
        MutableLiveData<List<Product>>()
    var listadoProductosPorUsuario: LiveData<List<Product>> = _listadoProductosPorUsuario

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private var _searchText: MutableLiveData<String> = MutableLiveData<String>()
    var searchText: LiveData<String> = _searchText


    //corrutina que llama a la api y carga el listado principal
    suspend fun getListadoProductos() {

        //   var listadoProductos= emptyList<Product>()

        var prod = Product()

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java).getProductsList()

                if (response.isNotEmpty()) {

                    //detenemos la carga
                    _isLoading.postValue(false)
                    _listadoProductos.postValue(response)
                    _listadoProductosInicial.postValue(response)

                    //esto nos servirá en caso de que hagmos una búsqueda.
                   // _listadoProductosBusqueda = _listadoProductos.value!!


                    Log.i("sos", prod.nombre)

                } else {
                    _listadoProductosInicial.postValue(emptyList())
                    Log.i("sos", "lista vacía")

                }


            } catch (e: Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")
            }
        }
    }


    fun searchProduct(searchText: String, listadoProductos:List<Product>) {
        _searchText.value = searchText
            _listadoProductos.value=listadoProductos.filter {
                it.nombre.contains(
                    _searchText.value!!,
                    ignoreCase = true
                )
        }
       // _searchText.value=""

    }

    fun restartProductslist(productsInit: List<Product>) {
        _listadoProductos.value=productsInit

    }
}

   // corrutina que llama a la api y carga el listado completo
//    suspend fun getListadoProductosPorUsuario(idUsuario: Int) {
//
//            viewModelScope.launch(Dispatchers.IO) {
//
//                try {
//                    //hacemos la llamada a la api
//                    var response = getRetrofit().create(ApiService::class.java).getProductsList()
//
//                    if (response.isNotEmpty()) {
//
//                        //detenemos la carga
//                        _isLoading.postValue(false)
//                        _listadoProductosBusqueda.postValue(response)
//                        Log.i("sos", "ha entrado bien en la corrutina")
//
//                    } else {
//                        _listadoProductosBusqueda.postValue(emptyList())
//                    }
//
//
//
//                } catch (e: Exception) {
//                    //en caso de error, muestra un mensaje
//                    // showError(context,e)
//
//                    Log.i("sos", "no ha entrado bien en la corrutina, $e")
//                }
//            }
//
//            // Creamos una lista temporal para almacenar los productos filtrados
//            val productosPorUsuario = mutableListOf<Product>()
//
//            //si el listado no está vacío
//            if (_listadoProductosBusqueda.value?.isNotEmpty() == true) {
//
//                //recorremos el valor del listado hasta que encontremos el que queremos
//                _listadoProductosBusqueda.value!!.forEach{ product: Product ->
//                    //por cada producto, si su idUsuario es igual al que recibe por parametro
//                    //lo añadimos a la lista
//                    if (product.idUsuario ==idUsuario) {
//                        productosPorUsuario.add(product)
//                    }
//                }
//
//                //igualamos ambas listas
//              _listadoProductosPorUsuario.value=productosPorUsuario
//            }
//        }
//}


