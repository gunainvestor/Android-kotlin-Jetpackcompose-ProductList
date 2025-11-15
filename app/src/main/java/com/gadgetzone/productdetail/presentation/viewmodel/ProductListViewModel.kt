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
                        ProductListUiState(
                            isLoading = false,
                            products = result.getOrNull() ?: emptyList(),
                            error = null
                        )
                    } else {
                        ProductListUiState(
                            isLoading = false,
                            products = emptyList(),
                            error = result.exceptionOrNull()?.message ?: "Unknown error occurred"
                        )
                    }
                }
                .collect { newState ->
                    _uiState.value = newState
                }
        }
    }
}

