package com.gadgetzone.productdetail.data.repository

import com.gadgetzone.productdetail.data.local.dao.ProductDao
import com.gadgetzone.productdetail.data.local.entity.toEntity
import com.gadgetzone.productdetail.data.remote.ProductApiService
import com.gadgetzone.productdetail.domain.model.Product
import com.gadgetzone.productdetail.domain.repository.ProductRepository
import com.gadgetzone.productdetail.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import java.net.UnknownHostException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao,
    private val networkUtils: NetworkUtils
) : ProductRepository {

    override fun getProducts(): Flow<Result<List<Product>>> = flow {
        // First, emit cached data if available
        val cachedProducts = productDao.getAllProducts().first()
        if (cachedProducts.isNotEmpty()) {
            emit(Result.success(cachedProducts.map { it.toDomain() }))
        }

        // Then try to fetch from network and update cache
        if (networkUtils.isNetworkAvailable()) {
            try {
                val remoteProducts = apiService.getProducts()
                val productEntities = remoteProducts.map { it.toDomain().toEntity() }
                
                // Update cache
                productDao.deleteAllProducts()
                productDao.insertProducts(productEntities)
                
                // Emit fresh data
                emit(Result.success(productEntities.map { it.toDomain() }))
            } catch (e: Exception) {
                // If we have cached data, return it even on network error
                val cached = productDao.getAllProducts().first()
                if (cached.isNotEmpty()) {
                    emit(Result.success(cached.map { it.toDomain() }))
                } else {
                    // No cache, emit error
                    val errorMessage = when (e) {
                        is UnknownHostException -> "Unable to resolve host. Please check your internet connection and DNS settings."
                        is ConnectException -> "Unable to connect to server. Please check your internet connection."
                        is SocketTimeoutException -> "Connection timeout. Please check your internet connection and try again."
                        else -> "Failed to load products: ${e.message ?: "Unknown error"}"
                    }
                    emit(Result.failure(Exception(errorMessage)))
                }
            }
        } else {
            // No network, check if we have cached data
            val cached = productDao.getAllProducts().first()
            if (cached.isEmpty()) {
                emit(Result.failure(Exception("No internet connection and no cached data available.")))
            }
        }
    }.catch { e ->
        // Fallback to cache if available
        try {
            val cached = productDao.getAllProducts().first()
            if (cached.isNotEmpty()) {
                emit(Result.success(cached.map { it.toDomain() }))
            } else {
                emit(Result.failure(Exception("Failed to load products: ${e.message ?: "Unknown error"}")))
            }
        } catch (cacheError: Exception) {
            emit(Result.failure(Exception("Failed to load products: ${e.message ?: "Unknown error"}")))
        }
    }

    override fun getProductById(id: Int): Flow<Result<Product>> = flow {
        // First, emit cached data if available
        val cachedProduct = productDao.getProductById(id).first()
        if (cachedProduct != null) {
            emit(Result.success(cachedProduct.toDomain()))
        }

        // Then try to fetch from network and update cache
        if (networkUtils.isNetworkAvailable()) {
            try {
                val remoteProduct = apiService.getProductById(id)
                val productEntity = remoteProduct.toDomain().toEntity()
                
                // Update cache
                productDao.insertProduct(productEntity)
                
                // Emit fresh data
                emit(Result.success(productEntity.toDomain()))
            } catch (e: Exception) {
                // If we have cached data, return it even on network error
                val cached = productDao.getProductById(id).first()
                if (cached != null) {
                    emit(Result.success(cached.toDomain()))
                } else {
                    // No cache, emit error
                    val errorMessage = when (e) {
                        is UnknownHostException -> "Unable to resolve host. Please check your internet connection and DNS settings."
                        is ConnectException -> "Unable to connect to server. Please check your internet connection."
                        is SocketTimeoutException -> "Connection timeout. Please check your internet connection and try again."
                        else -> "Failed to load product: ${e.message ?: "Unknown error"}"
                    }
                    emit(Result.failure(Exception(errorMessage)))
                }
            }
        } else {
            // No network, check if we have cached data
            val cached = productDao.getProductById(id).first()
            if (cached == null) {
                emit(Result.failure(Exception("No internet connection and no cached data available.")))
            }
        }
    }.catch { e ->
        // Fallback to cache if available
        try {
            val cached = productDao.getProductById(id).first()
            if (cached != null) {
                emit(Result.success(cached.toDomain()))
            } else {
                emit(Result.failure(Exception("Failed to load product: ${e.message ?: "Unknown error"}")))
            }
        } catch (cacheError: Exception) {
            emit(Result.failure(Exception("Failed to load product: ${e.message ?: "Unknown error"}")))
        }
    }
}

