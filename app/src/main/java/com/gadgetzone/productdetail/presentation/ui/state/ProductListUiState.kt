package com.gadgetzone.productdetail.presentation.ui.state

import com.gadgetzone.productdetail.domain.model.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)


