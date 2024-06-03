package com.example.tfg.feature_list.data.repo

import com.example.tfg.feature_list.data.data_source.ProductDao
import com.example.tfg.feature_list.domain.model.Product
import com.example.tfg.feature_list.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow

class ProductRepoImplementation (
    private val dao:ProductDao
): ProductRepo {
    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
    }

    override suspend fun getProductById(idProducto: Int): Product? {
       return dao.getProductById(idProducto)
    }

    override suspend fun insertProduct(product: Product) {
        return dao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return dao.deleteProduct(product)
    }
}