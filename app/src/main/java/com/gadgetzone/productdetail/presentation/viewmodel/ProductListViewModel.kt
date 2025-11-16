package com.gadgetzone.productdetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gadgetzone.productdetail.domain.usecase.GetProductsUseCase
import com.gadgetzone.productdetail.presentation.ui.state.ProductListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getProductsUseCase()
                .map { result ->
                    if (result.isSuccess) {
                        val products = result.getOrNull() ?: emptyList()
                        ProductListUiState(
                            isLoading = false,
                            products = products,
                            filteredProducts = filterProducts(products, _uiState.value.searchQuery),
                            searchQuery = _uiState.value.searchQuery,
                            error = null
                        )
                    } else {
                        ProductListUiState(
                            isLoading = false,
                            products = emptyList(),
                            filteredProducts = emptyList(),
                            searchQuery = _uiState.value.searchQuery,
                            error = result.exceptionOrNull()?.message ?: "Unknown error occurred"
                        )
                    }
                }
                .collect { newState ->
                    _uiState.value = newState
                }
        }
    }

    fun searchProducts(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            filteredProducts = filterProducts(_uiState.value.products, query)
        )
    }

    private fun filterProducts(products: List<com.gadgetzone.productdetail.domain.model.Product>, query: String): List<com.gadgetzone.productdetail.domain.model.Product> {
        if (query.isBlank()) {
            return products
        }
        val lowerQuery = query.lowercase()
        return products.filter { product ->
            product.title.lowercase().contains(lowerQuery) ||
            product.category.lowercase().contains(lowerQuery) ||
            product.description.lowercase().contains(lowerQuery)
        }
    }
}

