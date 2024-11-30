package com.example.tfg.core.models

import com.google.gson.annotations.SerializedName

data class NewUser(
    @SerializedName("nombreUsu") var nombreUsu:String="",
    @SerializedName("correo") var correo:String="",
    @SerializedName("foto") var picture:String=""){
}