package com.example.tfg.core.utils

/*
    Clase que se encarga de los tipos de respuesta posibles y contiene otras clases
 */
sealed class Resources<T> (val data:T?=null, val message:String?=null){

    //En caso de éxito
    class Success<T>(data:T): Resources<T>(data)

    class Error<T>(data: T?=null, message: String): Resources<T>(data, message)

    class Loading<T>(data: T?=null): Resources<T>(data)

}