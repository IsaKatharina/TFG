package com.example.tfg.feature_list.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tfg.feature_list.domain.model.Product

@Database(
    entities=[Product::class],
    version=1
)
abstract class ProductDatabase:RoomDatabase() {

    abstract val productDao=ProductDao

    companion object {
        const val DATABASE_NAME="bestdupe_db"
    }
}