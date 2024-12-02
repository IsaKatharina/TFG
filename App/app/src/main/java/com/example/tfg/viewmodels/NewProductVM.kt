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

    private val _og =MutableLiveData<String>()
    val og:LiveData<String> =_og

    private val _imagen=MutableLiveData<String>()
    val imagen:LiveData<String> =_imagen

    private val _addEnable=MutableLiveData<Boolean>()
    val addEnable:LiveData<Boolean> =_addEnable

    private val _goClicked=MutableLiveData<Boolean>()
    val goClicked:LiveData<Boolean> =_goClicked

//    fun setNombre(value: String) {
//        _nombre.value = value
//    }
//
//    fun setMarca(value: String) {
//        _marca.value = value
//    }
//
//    fun setNombreOG(value: String) {
//        _nombreOG.value = value
//    }
//
//    fun setMarcaOG(value: String) {
//        _marcaOG.value = value
//    }
//


    fun createProduct(nombre: String, marca: String,nombreOG: String, marcaOG: String, og:Boolean, imagen: String, goClicked:Boolean) {

        var idUsuario = 2

        _nombre.value = nombre
        _marca.value = marca
        _nombreOG.value = nombreOG
        _marcaOG.value = marcaOG

        if (og) {
            _og.value = "si"
        } else {
            _og.value = "no"
        }

        _imagen.value = imagen

        if (goClicked) {
            //creamos el producto.
            var newProduct = NewProduct(
                idUsuario, _nombre.value!!,
                _marca.value!!, _nombreOG.value!!, _marcaOG.value!!, _og.value!!, _imagen.value!!
            )

            //enviamos el producto a la api
            viewModelScope.launch(Dispatchers.IO) {

                try {
                    //hacemos la llamada a la api
                    var response = getRetrofit().create(ApiService::class.java).addProduct(newProduct)

                    //TODO:como controlo los duplicados??
                    if (response.equals(200)) {

                        //TODO:hay que hacer una ventana emergente?
                        Log.i("apiProd", "ha añadido bien el producto")
                    } else {
                        Log.i("apiProd", "no se ha añadido bien el producto,")

                    }
                    //Log.i("sos", "ha entrado bien en la corrutina")

                } catch (e: Exception) {
                    //en caso de error, muestra un mensaje
                    // showError(context,e)

                    Log.i("sos", "no ha entrado bien en la corrutina, $e")

                }
            }

        } else {

            Log.i("go", "no se ha pulsado el boton de añadir")
        }

    }



    }

    //TODO:necesitamos una función que parsee la img a String?
