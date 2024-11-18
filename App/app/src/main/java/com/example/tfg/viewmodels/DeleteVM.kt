package com.example.tfg.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteVM:ViewModel() {

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess


    fun deleteProduct(idProduct:Int) {

        //enviamos el id del producto a la api
        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java)
                    .deleteProduct(idProduct)

                //TODO:como controlo los duplicados??
                if (response.equals(200)) {
                    Log.i("delete", "ha borrado bien el producto")

                    _deleteSuccess.postValue(true)
                } else {
                    Log.i("edit", "no se ha borrado bien el producto, $response")
                    _deleteSuccess.postValue(true)

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