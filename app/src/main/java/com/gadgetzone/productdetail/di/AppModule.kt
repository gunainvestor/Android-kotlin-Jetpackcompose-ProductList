package com.gadgetzone.productdetail.di

import android.content.Context
import androidx.room.Room
import com.gadgetzone.productdetail.data.local.ProductDatabase
import com.gadgetzone.productdetail.data.local.dao.ProductDao
import com.gadgetzone.productdetail.data.remote.ProductApiService
import com.gadgetzone.productdetail.data.remote.RetrofitClient
import com.gadgetzone.productdetail.data.repository.ProductRepositoryImpl
import com.gadgetzone.productdetail.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApiService(): ProductApiService {
        return RetrofitClient.productApiService
    }

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ProductApiService,
        productDao: ProductDao,
        networkUtils: com.gadgetzone.productdetail.util.NetworkUtils
    ): ProductRepository {
        return ProductRepositoryImpl(apiService, productDao, networkUtils)
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): com.gadgetzone.productdetail.util.NetworkUtils {
        return com.gadgetzone.productdetail.util.NetworkUtils(context)
    }
}


