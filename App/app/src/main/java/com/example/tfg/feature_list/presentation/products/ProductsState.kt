package com.example.tfg.feature_list.presentation.products

import com.example.tfg.feature_list.domain.model.Product
import com.example.tfg.feature_list.domain.util.OrderType
import com.example.tfg.feature_list.domain.util.ProductOrder

//hacemos una data class que contiene los posibles estados de nuestra lista principal.
data class ProductsState(
    val productsList:List<Product> = emptyList(),
    val productOrder: ProductOrder=ProductOrder.NumberOfFavs(OrderType.Descending),
    val isOrderSectionVisible:Boolean=false
)
