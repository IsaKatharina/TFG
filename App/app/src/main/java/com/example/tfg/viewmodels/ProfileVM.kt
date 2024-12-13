package com.example.tfg.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.Product
import com.example.tfg.core.models.User
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileVM:ViewModel() {

    private var _userFound:MutableLiveData<User> = MutableLiveData<User>()
    var userFound:LiveData<User> =_userFound

    private var _listadoProductosPorUsuario: MutableLiveData<List<Product>> =
        MutableLiveData<List<Product>>()
    var listadoProductosPorUsuario: LiveData<List<Product>> = _listadoProductosPorUsuario

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    //corrutina que llama a la api y busca el usuario que necesitamos
    suspend fun getUser(userMail:String) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java).getUserByMail(userMail)

                if (response.correo==userMail) {
                    _userFound.postValue(response)

                    Log.i("sos", response.nombreUsu)

                    if (response.idUsuario !=0) {

                    }

                } else {

                    Log.i("sos", "ha ocurrido un error")

                }
            } catch (e: Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")
            }
        }
    }

    //corrutina que llama a la api y carga el listado completo
    suspend fun getListadoProductosPorUsuario(idUsuario: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java)
                    .getProductsListByUser(idUsuario)

                if (response.isNotEmpty()) {

                    //detenemos la carga
                    _isLoading.postValue(false)
                    _listadoProductosPorUsuario.postValue(response)
                    Log.i("sos", "ha entrado bien en la corrutina")

                } else {
                    _isLoading.postValue(false)
                    _listadoProductosPorUsuario.postValue(emptyList())
                }


            } catch (e: Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")
            }
        }
    }

}