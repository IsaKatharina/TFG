package com.example.tfg.dal.remote.utils

import com.example.tfg.core.models.NewProduct
import com.example.tfg.core.models.NewUser
import com.example.tfg.core.models.Product
import com.example.tfg.core.models.User
import com.example.tfg.dal.remote.responses.ProductsList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

//constante que guarda la url de la api
private const val BASE_URL="https://bestdupeapp.azurewebsites.net/api/"

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

    @GET("usuarios/{idUsuario}/productos")
    suspend fun getProductsListByUser(@Path("idUsuario")idUsuario:Int):List<Product>

    @GET("usuarios/{correo}")
    suspend fun getUserByMail(@Path("correo")correo:String): User

    @POST("productos")
    suspend fun addProduct(
        @Body newProduct: NewProduct
    )

    @POST("usuarios")
    suspend fun addUser(
        @Body newUser: NewUser
    )

    @PUT("productos/{idProduct}")
    suspend fun editProduct(
        @Path("idProduct")idProduct: Int,
        @Body product: Product
    )

    @DELETE("productos/{idProduct}")
    suspend fun deleteProduct(
        @Path("idProduct")idProduct: Int
    )
}