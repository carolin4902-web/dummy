package com.sena.dummy.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sena.dummy.domain.repository.ProductRepository
import com.sena.dummy.ui.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init { loadProducts() }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessages =  null) }
            runCatching { repository.getProducts() }
                .onSuccess { list -> _uiState.update { it.copy(isLoading = false, products = list) } }
                .onFailure { e -> _uiState.update { it.copy(isLoading = false, errorMessages = e.message ?: "No  se pudieron cargar los productos") } }
        }
    }
}