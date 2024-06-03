package com.example.tfg.feature_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val idProducto: Int,
    @PrimaryKey val idUsuario:Int,
    val nombre:String,
    val marca:String,
    val nombreOG:String,
    val marcaOG:String,
    val original:String,
    val comentario:String,
    val imagen:String,
    val numFavs:Int
)
