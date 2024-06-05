package com.example.tfg.data.remote.responses

data class Product(
    val comentario: String,
    val idProducto: Int,
    val idUsuario: Int,
    val imagen: String,
    val marca: String,
    val marcaOG: String,
    val nombre: String,
    val nombreOG: String,
    val original: String
)