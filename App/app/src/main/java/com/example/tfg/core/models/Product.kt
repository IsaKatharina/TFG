package com.example.tfg.core.models

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName ("idProduct") var idProduct:Int=0,
    @SerializedName("nombre") var title:String="",
    @SerializedName ("img") var body:String="")

