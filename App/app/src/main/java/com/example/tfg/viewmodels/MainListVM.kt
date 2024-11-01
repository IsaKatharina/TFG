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
import com.example.tfg.dal.remote.utils.Resources
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
    var listadoProductos: LiveData<List<Product>> =_listadoProductos

    private var _listaProductosBusqueda: MutableLiveData<List<Product>> =MutableLiveData<List<Product>>()
    var listadoProductosBusqueda: LiveData<List<Product>> =_listaProductosBusqueda

    private var _isLoading= MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> =_isLoading

    //corrutina que llama a la api y carga el listado principal
  suspend fun getListadoProductos(){

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response= getRetrofit().create(ApiService::class.java).getProductsList()

                if (response.isNotEmpty()){

                    //detenemos la carga
                    _isLoading.postValue(false)
                    _listadoProductos.postValue(response)

                } else {
                    _listadoProductos.postValue(emptyList())
                }

                Log.i("sos", "ha entrado bien en la corrutina")

            } catch (e:Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")
            }
        }

    }

    //buscamos un producto dentro de un listado y devolvemos el producto encontrado.
    //si no encuentra ningún producto que coincida, devolverá un null
    fun getProductbyId(idProduct:Int): Product? {

        var productFound:Product?=Product()

        //si el listado no está vacío
        if(listadoProductos.value?.isEmpty()==false) {

            //recorremos el valor del listado hasta que encontremos el que queremos
            productFound= _listadoProductos.value?.find { it.idProduct==idProduct }

        }
        return productFound
    }

}


