package com.example.tfg.viewmodels

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg.core.models.NewProduct
import com.example.tfg.core.models.NewUser
import com.example.tfg.dal.remote.utils.ApiService
import com.example.tfg.dal.remote.utils.getRetrofit
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserVM:ViewModel() {

    private val _email= MutableLiveData <String>()
    val email: LiveData<String> =_email

    private val _nombreUsu= MutableLiveData<String>()
    val nombreUsu:LiveData<String> =_nombreUsu

    private val _pic=MutableLiveData<String>()
    val pic:LiveData<String> = _pic

    private val _password= MutableLiveData<String>()
    val password: LiveData<String> =_password

    private val _newUserEnable= MutableLiveData<Boolean>()
    val newUserEnable: LiveData<Boolean> =_newUserEnable

    private val auth: FirebaseAuth = Firebase.auth

    private val _welcomeClicked =MutableLiveData<Boolean>()
    val welcomeClicked: LiveData<Boolean> = _welcomeClicked

    //función que comprueba que se ingresa un email.
    //devuelve true o false.
    private fun isValidEmail(email:String):Boolean {
        var validEmail:Boolean=false
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            validEmail=true
        }
        return validEmail
    }

    //función que compruba que la contraseña introducida sea mayor que 6.
    //devuelve true o false.
    private fun isValidPassword(password: String):Boolean {
        var validPassword:Boolean=false

        if (password.length>6) {
            validPassword=true
        }
        return validPassword
    }

    private fun isValidNombreUsu(nombreUsu: String):Boolean {
        var validNombreUsu:Boolean=false

        if (nombreUsu!="") {
            validNombreUsu=true
        }
        return validNombreUsu
    }

    fun onNewUserChanged(email:String, nombreUsu: String, pic: String, password: String) {
        _email.value=email
        _password.value=password
        _nombreUsu.value=nombreUsu
        _pic.value=pic

        _newUserEnable.value=isValidEmail(email)&&isValidPassword(password)&&isValidNombreUsu(nombreUsu)


    }

    //esta función es la que crea el usuario en la base de datos
    fun createUser (email:String, nombreUsu:String, pic:String) {

        _email.value = email
        _nombreUsu.value = nombreUsu
        _pic.value = pic

        //creamos el producto.
        var newUser = NewUser(
            _nombreUsu.value!!,
            _email.value!!, _pic.value!!
        )

        //enviamos el producto a la api
        viewModelScope.launch(Dispatchers.IO) {

            try {
                //hacemos la llamada a la api
                var response = getRetrofit().create(ApiService::class.java).addUser(newUser)

                //TODO:como controlo los duplicados??
                if (response.equals(200)) {

                    //TODO:hay que hacer una ventana emergente?
                    Log.i("apiUser", "ha añadido bien el usuario")
                } else {
                    Log.i("apiUser", "no se ha añadido bien el usuario, $response")

                }
                Log.i("sos", "ha entrado bien en la corrutina")

            } catch (e: Exception) {
                //en caso de error, muestra un mensaje
                // showError(context,e)

                Log.i("sos", "no ha entrado bien en la corrutina, $e")

            }
        }
    }


}