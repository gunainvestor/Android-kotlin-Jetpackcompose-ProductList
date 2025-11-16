package com.gadgetzone.productdetail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gadgetzone.productdetail.data.local.dao.ProductDao
import com.gadgetzone.productdetail.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

