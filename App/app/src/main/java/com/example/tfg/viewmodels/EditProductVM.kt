package com.example.tfg.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProductVM:ViewModel() {


    //corrutina que edita un producto
    fun editProduct(product:Product, nombre:String, marca:String, nombreOG:String, marcaOG:String, imagen:String) {

        //tenemos que detectar si los valores son distintos
        if (product.nombre!=nombre){
            product.nombre =nombre

        }

        if (product.marca!=marca){
            product.marca =marca

        }

        if (product.nombreOG!=nombreOG){
            product.nombreOG =nombreOG

        }

        if (product.marcaOG!=marcaOG){
            product.marcaOG =marcaOG

        }

//        if (og) {
//            _og.value="si"
//        } else {
//            _og.value="no"
//        }
        if (product.imagen!=imagen){
            product.imagen =imagen

        }

        //enviamos el producto a la api
        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java)
                    .editProduct(product.idProduct, product)

                //TODO:como controlo los duplicados??
                if (response.equals(200)) {
                    Log.i("edit", "ha editado bien el producto")
                } else {
                    Log.i("edit", "no se ha editado bien el producto, $response")

                }
                Log.i("go", "ha entrado bien en la corrutina")

            } catch (e: Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")

            }
        }

    }


}