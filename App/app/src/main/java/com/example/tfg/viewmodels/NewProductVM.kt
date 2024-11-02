package com.example.tfg.viewmodels

import android.util.Log
import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.NewProduct
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewProductVM:ViewModel() {

    //creamos las variables que necesitará nuestra vista
    //private val serán las del vm
    //val las de la vista
    private val _nombre=MutableLiveData<String>()
    val nombre: LiveData<String> =_nombre

    private val _marca=MutableLiveData<String>()
    val marca:LiveData<String> =_marca

    private val _nombreOG=MutableLiveData<String>()
    val nombreOG: LiveData<String> =_nombreOG

    private val _marcaOG= MutableLiveData<String>()
    val marcaOG:LiveData<String> =_marcaOG

    private val _og =MutableLiveData<String>("no")
    val og:LiveData<String> =_og

    private val _imagen=MutableLiveData<String>()
    val imagen:LiveData<String> =_imagen


    //comprueba si es original o no
    //lo cambiamos a String que es lo que entiende la badat
    fun checkOG() {
        _og.value="si"
    }

    //TODO:necesitamos una función que parsee la img a String?
   suspend fun addProduct(idUsuario:Int, nombre:String, marca:String, nombreOG:String, marcaOG:String, imagen:String) {

        var newProduct = NewProduct(idUsuario, nombre, marca, nombreOG, marcaOG, imagen)

       viewModelScope.launch(Dispatchers.IO) {

           try {
               //hacemos la llamada a la api
               var response= getRetrofit().create(ApiService::class.java).addProduct(newProduct)

               //TODO:como controlo los duplicados??
               if (response.equals(200)) {

                   //TODO:hay que hacer una ventana emergente?
                   Log.i("api","ha añadido bien el producto")
               } else  {
                   Log.i("api","no se ha añadido bien el producto, $response")

               }
               Log.i("sos", "ha entrado bien en la corrutina")

           } catch (e:Exception) {
               //en caso de error, muestra un mensaje
               // showError(context,e)

               Log.i("sos", "no ha entrado bien en la corrutina, $e")

           }
       }

    }
}