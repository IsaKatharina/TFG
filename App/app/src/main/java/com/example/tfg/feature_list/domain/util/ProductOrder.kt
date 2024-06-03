package com.example.tfg.feature_list.domain.util

//esta clase nos permite ordenar los productos por título (ascendente o descente)
sealed class ProductOrder (val orderType: OrderType) {
    class Nombre (orderType: OrderType):ProductOrder (orderType)
    class NumberOfFavs(orderType: OrderType):ProductOrder(orderType)
}