package com.example.tfg.repo

import com.example.tfg.dal.remote.utils.Resources
import com.example.tfg.dal.remote.responses.Product
import com.example.tfg.dal.remote.responses.ProductsList
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