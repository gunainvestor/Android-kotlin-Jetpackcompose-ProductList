package com.gadgetzone.productdetail.domain.repository

import com.gadgetzone.productdetail.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Result<List<Product>>>
    fun getProductById(id: Int): Flow<Result<Product>>
}


