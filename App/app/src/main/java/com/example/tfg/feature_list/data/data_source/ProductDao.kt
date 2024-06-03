package com.example.tfg.feature_list.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tfg.feature_list.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("Select * from Product")
    fun getProducts(): Flow<List<Product>>

    //Es una suspend fun porque devuelve un objeto
    @Query("Select * from Product where idProducto=:idProducto")
    suspend fun  getProductById(idProducto: Int):Product?//en caso de que idProducto sea nulo o no exista

    //hacemos un insert que "sustituirá" el objeto si ya existe.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}