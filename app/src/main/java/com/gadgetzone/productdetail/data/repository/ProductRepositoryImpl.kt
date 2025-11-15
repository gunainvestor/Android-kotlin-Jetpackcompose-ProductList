package com.gadgetzone.productdetail.data.repository

import com.gadgetzone.productdetail.data.remote.ProductApiService
import com.gadgetzone.productdetail.domain.model.Product
import com.gadgetzone.productdetail.domain.repository.ProductRepository
import com.gadgetzone.productdetail.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import java.net.UnknownHostException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
    private val networkUtils: NetworkUtils
) : ProductRepository {

    override fun getProducts(): Flow<Result<List<Product>>> = flow {
        if (!networkUtils.isNetworkAvailable()) {
            emit(Result.failure(Exception("No internet connection. Please check your network settings and try again.")))
            return@flow
        }
        emit(Result.success(apiService.getProducts().map { it.toDomain() }))
    }.catch { e ->
        val errorMessage = when (e) {
            is UnknownHostException -> "Unable to resolve host. Please check your internet connection and DNS settings."
            is ConnectException -> "Unable to connect to server. Please check your internet connection."
            is SocketTimeoutException -> "Connection timeout. Please check your internet connection and try again."
            else -> "Failed to load products: ${e.message ?: "Unknown error"}"
        }
        emit(Result.failure(Exception(errorMessage)))
    }

    override fun getProductById(id: Int): Flow<Result<Product>> = flow {
        if (!networkUtils.isNetworkAvailable()) {
            emit(Result.failure(Exception("No internet connection. Please check your network settings and try again.")))
            return@flow
        }
        emit(Result.success(apiService.getProductById(id).toDomain()))
    }.catch { e ->
        val errorMessage = when (e) {
            is UnknownHostException -> "Unable to resolve host. Please check your internet connection and DNS settings."
            is ConnectException -> "Unable to connect to server. Please check your internet connection."
            is SocketTimeoutException -> "Connection timeout. Please check your internet connection and try again."
            else -> "Failed to load product: ${e.message ?: "Unknown error"}"
        }
        emit(Result.failure(Exception(errorMessage)))
    }
}

