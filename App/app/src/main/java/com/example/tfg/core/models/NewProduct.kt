package com.example.tfg.core.models

import com.google.gson.annotations.SerializedName

data class NewProduct (
    @SerializedName("idUsuario") var idUsuario:Int=0,
    @SerializedName("nombre") var nombre:String="",
    @SerializedName("marca") var marca:String="",
    @SerializedName("nombreOG") var nombreOG:String="",
    @SerializedName("marcaOG") var marcaOG:String="",
    @SerializedName("original") var original:String="",
    @SerializedName("comentario") var comentario:String="no",
    @SerializedName("imagen") var imagen:String=""){}