package com.sena.dummy.ui.state

import com.sena.dummy.domain.model.ProductModel

data class ProductUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val products: List<ProductModel> = emptyList(),
    val selectedProduct: ProductModel? = null,
    val errorMessages: String? = null,   // error al CARGAR la lista (pantalla completa)
    val actionError: String? = null,     // error al crear / editar / eliminar (snackbar)
    val successMessages: String? = null
)