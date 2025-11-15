package com.gadgetzone.productdetail.presentation.ui.state

import com.gadgetzone.productdetail.domain.model.Product

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)


