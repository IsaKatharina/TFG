package com.example.tfg.di

import android.app.Application
import androidx.room.Room
import com.example.tfg.feature_list.data.data_source.ProductDao
import com.example.tfg.feature_list.data.data_source.ProductDatabase
import com.example.tfg.feature_list.data.repo.ProductRepoImplementation
import com.example.tfg.feature_list.domain.repo.ProductRepo
import com.example.tfg.feature_list.domain.use_case.DeleteProduct
import com.example.tfg.feature_list.domain.use_case.GetProducts
import com.example.tfg.feature_list.domain.use_case.ProductsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    @Singleton
    fun provideProductDatabase(app:Application):ProductDatabase{
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            ProductDatabase.DATABASE_NAME

        ).build()
    }

    @Provides
    @Singleton
    fun provideProductsRepo(
        db:ProductDatabase
    ): ProductRepo{
        return ProductRepoImplementation(db.productDao)
    }

    @Provides
    @Singleton
    fun provideProductsUseCases(repo: ProductRepo):ProductsUseCases{
        return ProductsUseCases(
            getProducts = GetProducts(repo),
            deleteProduct = DeleteProduct(repo)
        )
    }

}