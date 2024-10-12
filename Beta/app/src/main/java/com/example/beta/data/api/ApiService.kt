package com.example.beta.data.api

import com.example.beta.models.Product
import com.example.beta.models.ProductsList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("productos")
    suspend fun getProductsList (): ProductsList

    @GET("productos/{idProduct}")
    suspend fun getProductsDetails (
        @Path("idProduct") idProduct:Int
    ): Product
}