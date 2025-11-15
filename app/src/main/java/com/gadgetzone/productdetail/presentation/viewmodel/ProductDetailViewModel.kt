package com.gadgetzone.productdetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gadgetzone.productdetail.domain.usecase.GetProductByIdUseCase
import com.gadgetzone.productdetail.presentation.ui.state.ProductDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getProductByIdUseCase(productId)
                .map { result ->
                    if (result.isSuccess) {
                        ProductDetailUiState(
                            isLoading = false,
                            product = result.getOrNull(),
                            error = null
                        )
                    } else {
                        ProductDetailUiState(
                            isLoading = false,
                            product = null,
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

