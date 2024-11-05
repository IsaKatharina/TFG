package com.example.tfg.viewmodels

import android.util.Log
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

class EditProductVM:ViewModel() {
    //creamos las variables que necesitará nuestra vista
    //private val serán las del vm
    //val las de la vista
    private val _idProduct=MutableLiveData<Int>()
    val idProduct:LiveData<Int> =_idProduct

    private val _idUsuario=MutableLiveData<Int>()
    val idUsuario:LiveData<Int> =_idUsuario

    private val _nombre= MutableLiveData<String>()
    val nombre: LiveData<String> =_nombre

    private val _marca= MutableLiveData<String>()
    val marca: LiveData<String> =_marca

    private val _nombreOG= MutableLiveData<String>()
    val nombreOG: LiveData<String> =_nombreOG

    private val _marcaOG= MutableLiveData<String>()
    val marcaOG: LiveData<String> =_marcaOG

    private val _og = MutableLiveData<String>()
    val og: LiveData<String> =_og

    private val _imagen= MutableLiveData<String>()
    val imagen: LiveData<String> =_imagen

    private val _addEnable= MutableLiveData<Boolean>()
    val addEnable: LiveData<Boolean> =_addEnable

    private val _editClicked= MutableLiveData<Boolean>()
    val editClicked: LiveData<Boolean> =_editClicked

    private val _product=MutableLiveData<Product>()
    val product:LiveData<Product> =_product

    private val _productEditado:MutableLiveData<Product> =MutableLiveData<Product>()
    val productEditado:LiveData<Product> =_productEditado

    //corrutina que edita un producto
    fun editProduct(productEditado: Product, editClicked:Boolean) {

        _product.value=productEditado

        if (editClicked) {

            //enviamos el producto a la api
            viewModelScope.launch(Dispatchers.IO) {

                try {
                    //hacemos la llamada a la api
                    var response = getRetrofit().create(ApiService::class.java).editProduct(_product.value!!.idProduct, _product.value!!)

                    //TODO:como controlo los duplicados??
                    if (response.equals(200)) {

                        //TODO:hay que hacer una ventana emergente?
                        Log.i("apiProd", "ha editado bien el producto")
                    } else {
                        Log.i("apiProd", "no se ha editado bien el producto, $response")

                    }
                    Log.i("sos", "ha entrado bien en la corrutina")

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