package com.example.tfg.repo

import com.example.tfg.core.utils.Resources
import com.example.tfg.data.remote.api
import com.example.tfg.data.remote.responses.Product
import com.example.tfg.data.remote.responses.ProductsList
//import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//@ActivityScoped
class ProductsRepoImp @Inject constructor(
    private val api:api
) {
    suspend fun getProductsList (limit:Int, offset:Int): Resources<ProductsList> {
        val response = try {
            api.getProductsList(limit, offset)
        }catch (e:Exception){
            return Resources.Error(null, "An error has ocurred.")

        }
        return  Resources.Success(response)
    }

    suspend fun getProductsDetails (idProduct: Int): Resources<Product> {
        val response = try {
            api.getProductsDetails(idProduct)
        }catch (e:Exception){
            return Resources.Error(null, "An error has ocurred.")

        }
        return  Resources.Success(response)
    }


}