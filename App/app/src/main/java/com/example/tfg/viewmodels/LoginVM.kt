package com.example.tfg.viewmodels

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

//esto es una clase vm que hereda de ViewModel
class LoginVM:ViewModel(){

    //creamos las variables que necesitará nuestra vista
    //private val serán las del vm
    //val las de la vista
    private val _email=MutableLiveData <String>()
    val email:LiveData<String> =_email

    private val _password=MutableLiveData<String>()
    val password:LiveData<String> =_password

    private val _loginEnable=MutableLiveData<Boolean>()
    val loginEnable:LiveData<Boolean> =_loginEnable

    private val _isLoading= MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> =_isLoading

    private val _loginTrue=MutableLiveData<Boolean>()
    val loginTrue:LiveData<Boolean> =_loginTrue

    //función que comprueba que el email y la contraseña son válidos.
    //si lo son, se pone disponible el botón de login.
    fun onLoginChanged (email: String, password:String) {
        _email.value=email
        _password.value=password
        _loginEnable.value=isValidEmail(email)&&isValidPassword(password)
    }

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

    //función que checkea que uno se puede loggear
    private fun checkLogin (loginTrue:Boolean):Boolean {

        return loginTrue
    }
}