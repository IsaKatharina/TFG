package com.example.tfg.dal.remote.utils

import com.example.tfg.core.models.NewProduct
import com.example.tfg.core.models.Product
import com.example.tfg.dal.remote.responses.ProductsList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

//constante que guarda la url de la api
private const val BASE_URL="https://bestdupe.azurewebsites.net/api/"

//objeto de retrofit que convierte el string del JSON en el tipo que queramos.
fun getRetrofit():Retrofit{
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

interface ApiService {

    @GET("productos")
    suspend fun getProductsList (): List<Product>

    @GET("productos/{idProduct}")
    suspend fun getProductById(@Path("idProduct")idProduct: Int):Product

    @GET("productos/{idUsuario}")
    suspend fun getProductsListByUser(@Path("idUsuario")idUsuario:Int):List<Product>

    @POST("productos")
    suspend fun addProduct(
        @Body newProduct: NewProduct
    )

    @PUT("productos/{idProduct}")
    suspend fun editProduct(
        @Path("idProduct")idProduct: Int,
        @Body product: Product
    )


}