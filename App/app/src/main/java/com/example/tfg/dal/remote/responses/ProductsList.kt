package com.example.tfg.dal.remote.responses

import com.google.gson.annotations.SerializedName

data class ProductsList(
    @SerializedName("nombre") var nombre:String,
    @SerializedName("img") var imagen:String
)