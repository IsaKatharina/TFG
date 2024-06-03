package com.example.tfg.feature_list.domain.use_case

//esto lo hacemos para "inyectar" nuestros use-case
//en el vm correspondiente.
data class ProductsUseCases(
    val getProducts: GetProducts,
    val deleteProduct: DeleteProduct
)