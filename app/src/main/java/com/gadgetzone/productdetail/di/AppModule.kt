package com.gadgetzone.productdetail.di

import com.gadgetzone.productdetail.data.remote.ProductApiService
import com.gadgetzone.productdetail.data.remote.RetrofitClient
import com.gadgetzone.productdetail.data.repository.ProductRepositoryImpl
import com.gadgetzone.productdetail.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideProductRepository(
        apiService: ProductApiService,
        networkUtils: com.gadgetzone.productdetail.util.NetworkUtils
    ): ProductRepository {
        return ProductRepositoryImpl(apiService, networkUtils)
    }
}


