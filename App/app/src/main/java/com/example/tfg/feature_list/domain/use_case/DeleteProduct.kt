package com.example.tfg.feature_list.domain.use_case

import com.example.tfg.feature_list.domain.model.Product
import com.example.tfg.feature_list.domain.repo.ProductRepo

class DeleteProduct(
    private val repo: ProductRepo
) {
    suspend operator fun invoke (product: Product){
        repo.deleteProduct(product)
    }
}