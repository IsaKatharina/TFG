package com.example.tfg.feature_list.presentation.products

import com.example.tfg.feature_list.domain.util.ProductOrder

sealed class ProductsEvents {
    data class Order(val productOrder: ProductOrder): ProductsEvents()
    data class Delete
}