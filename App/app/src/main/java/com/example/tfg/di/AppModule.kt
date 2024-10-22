package com.example.tfg.di



//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

////@Module
////@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Singleton
//    fun provideProductsRepo(
//        api:api
//    ) = ProductsRepoImp(api)
//
//    fun provideApi():api {
//
//        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(api::class.java)
//    }
//
//
//}