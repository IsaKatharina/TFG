package com.example.tfg.dal.remote.utils

import com.example.tfg.dal.remote.responses.Product
import com.example.tfg.dal.remote.responses.ProductsList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//constante que guarda la url de la api
private const val BASE_URL="https://bestdupe.azurewebsites.net/api/"

//objeto de retrofit que convierte el string del JSON en el tipo que queramos.
private val retrofit=Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

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