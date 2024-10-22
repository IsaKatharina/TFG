package com.example.tfg.core.models

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("nombre") var title:String,
    @SerializedName ("img") var body:String)

