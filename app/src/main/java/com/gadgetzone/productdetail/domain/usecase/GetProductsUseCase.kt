package com.gadgetzone.productdetail.domain.usecase

import com.gadgetzone.productdetail.domain.model.Product
import com.gadgetzone.productdetail.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Result<List<Product>>> {
        return repository.getProducts()
    }
}


