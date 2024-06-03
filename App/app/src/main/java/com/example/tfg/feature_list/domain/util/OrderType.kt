package com.example.tfg.feature_list.domain.util

sealed class OrderType {
    object Ascending:OrderType()
    object Descending: OrderType()

}