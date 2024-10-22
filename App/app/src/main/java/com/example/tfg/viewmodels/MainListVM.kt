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

    private var _listadoProductosPrueba: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    var listadoProductosPruebas: MutableLiveData<List<Product>> =_listadoProductosPrueba

    private var _isLoading= MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> =_isLoading

    //corrutina que llama a la api y carga el listado principal
    suspend fun getListadoProductos(){

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response= getRetrofit().create(ApiService::class.java).getProductsList()

                if (!response.isEmpty()){

                    //detenemos la carga
                    _isLoading.postValue(false)
                    _listadoProductosPrueba.postValue(response)

                } else {
                    _listadoProductosPrueba.postValue(emptyList())
                }

                //TODO:transformar la respuesta en un listado de productos
                //_listadoProductosPrueba=MutableLiveData(response)
                Log.i("sos", "ha entrado bien en la corrutina")

            } catch (e:Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)
                //_listadoProductosPrueba= MutableLiveData(emptyList())
                Log.i("sos", "no ha entrado bien en la corrutina, $e")
            }
        }

    }



//    private fun showError() {
//        Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_SHORT).show()
//    }

}