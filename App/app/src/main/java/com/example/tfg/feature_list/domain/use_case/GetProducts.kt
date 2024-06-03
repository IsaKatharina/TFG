package com.example.tfg.feature_list.domain.use_case

import com.example.tfg.feature_list.domain.model.Product
import com.example.tfg.feature_list.domain.repo.ProductRepo
import com.example.tfg.feature_list.domain.util.OrderType
import com.example.tfg.feature_list.domain.util.ProductOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProducts(
    private val repo: ProductRepo
) {
    //cada use-case debe tener una sola función pública.
    //en este caso, queremos que nos devuelva una lista de productos.
    operator fun invoke(
        //por defecto se ordenará por el número de likes en descendente.
        productOrder: ProductOrder=ProductOrder.NumberOfFavs(OrderType.Descending)
    ): Flow<List<Product>> {
        return repo.getProducts().map { products->
            when (productOrder.orderType) {
                is OrderType.Ascending -> {
                    when(productOrder){
                        is ProductOrder.NumberOfFavs-> products.sortedBy {it.numFavs}
                        is ProductOrder.Nombre-> products.sortedBy { it.nombre }
                    }
                }
                is OrderType.Descending ->{
                    when(productOrder){
                        is ProductOrder.NumberOfFavs-> products.sortedByDescending {it.numFavs}
                        is ProductOrder.Nombre-> products.sortedByDescending { it.nombre }
                    }
                }
        }

         }
    }
}