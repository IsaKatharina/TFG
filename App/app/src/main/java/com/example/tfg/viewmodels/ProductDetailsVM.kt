package com.example.tfg.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Optional.empty

class ProductDetailsVM:ViewModel() {

    //TODO: hay que poner un check de que es el original

    private var _productFound:MutableLiveData<Product> = MutableLiveData<Product>()
    var productFound:LiveData<Product> =_productFound

    private var _isLoading= MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> =_isLoading

    //corrutina que llama a la api y carga el listado principal
    suspend fun getProduct(idProduct:Int){

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response= getRetrofit().create(ApiService::class.java).getProductById(idProduct)

                if (response.idProduct == idProduct){

                    _productFound.postValue(response)

                } else {
                    //devolvemos un producto vacío
                    _productFound.postValue(Product())
                }
                Log.i("sos", "ha entrado bien en la corrutina, ${response.idProduct}")

            } catch (e:Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")

            } finally {
                _isLoading.postValue(false)
            }
        }

    }


}