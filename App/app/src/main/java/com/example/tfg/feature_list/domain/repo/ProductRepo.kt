package com.example.tfg.feature_list.domain.repo

import com.example.tfg.feature_list.domain.model.Product
import kotlinx.coroutines.flow.Flow


interface ProductRepo {

    //se utiliza una interfaz para los use-case, es decir, el testing.
    //para no tener que acceder a una bbdd o una api cada vez que queremos testear.

    fun getProducts():Flow<List<Product>>

    suspend fun getProductById(idProducto: Int):Product?

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}

