package com.example.tfg.data.remote

import com.example.tfg.data.remote.responses.Product
import com.example.tfg.data.remote.responses.ProductsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface api {

    @GET("productos")
    suspend fun getProductsList (
        @Query("limit") limit:Int,
        @Query("offset") offset: Int
    ): ProductsList

    @GET("productos/{idProduct}")
    suspend fun getProductsDetails (
        @Path("idProduct") idProduct:Int
    ): Product
}