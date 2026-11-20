package com.sena.dummy.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.dummy.domain.model.ProductModel
import com.sena.dummy.domain.useCase.CreateProductUseCase
import com.sena.dummy.domain.useCase.DeleteProductUseCase
import com.sena.dummy.domain.useCase.GetProductUseCase
import com.sena.dummy.domain.useCase.UpdateProductUseCase
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
    private val getProductsUseCase: GetProductUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    // DummyJSON SIMULA los cambios: no los guarda en el servidor.
    // Los productos creados por nosotros solo existen en la lista local,
    // y para editarlos/eliminarlos no se llama a la API (daría 404).
    private val localIds = mutableSetOf<Int>()

    init { loadProducts() }

    // ---------- READ ----------
    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessages = null) }
            runCatching { getProductsUseCase() }
                .onSuccess { list ->
                    localIds.clear()
                    _uiState.update { it.copy(isLoading = false, products = list) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessages = e.message ?: "No se pudieron cargar los productos"
                        )
                    }
                }
        }
    }

    // ---------- CREATE ----------
    fun createProduct(title: String, description: String, category: String, price: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            val newProduct = ProductModel(
                id = 0,
                title = title,
                description = description,
                category = category,
                price = price
            )
            runCatching { createProductUseCase(newProduct) }
                .onSuccess { created ->
                    // La API siempre devuelve el mismo id (195): generamos uno local único
                    val localId = (_uiState.value.products.maxOfOrNull { it.id } ?: 0) + 1
                    localIds.add(localId)
                    val product = created.copy(id = localId)
                    _uiState.update {
                        it.copy(
                            isSaving = false,
                            products = listOf(product) + it.products,
                            successMessages = "Producto creado"
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isSaving = false, actionError = e.message ?: "No se pudo crear el producto")
                    }
                }
        }
    }

    // ---------- UPDATE ----------
    fun updateProduct(product: ProductModel) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            runCatching {
                if (product.id in localIds) product else updateProductUseCase(product)
            }
                .onSuccess { updated ->
                    val final = updated.copy(id = product.id, imageUrl = product.imageUrl)
                    _uiState.update { state ->
                        state.copy(
                            isSaving = false,
                            products = state.products.map { if (it.id == final.id) final else it },
                            successMessages = "Producto actualizado"
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isSaving = false, actionError = e.message ?: "No se pudo actualizar el producto")
                    }
                }
        }
    }

    // ---------- DELETE ----------
    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            runCatching {
                if (id !in localIds) deleteProductUseCase(id)
            }
                .onSuccess {
                    localIds.remove(id)
                    _uiState.update { state ->
                        state.copy(
                            isSaving = false,
                            products = state.products.filterNot { it.id == id },
                            successMessages = "Producto eliminado"
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isSaving = false, actionError = e.message ?: "No se pudo eliminar el producto")
                    }
                }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(successMessages = null, actionError = null) }
    }
}