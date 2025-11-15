package com.gadgetzone.productdetail.presentation.ui.state

import com.gadgetzone.productdetail.domain.model.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)


