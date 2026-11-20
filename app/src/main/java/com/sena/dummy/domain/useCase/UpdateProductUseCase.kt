package com.sena.dummy.domain.useCase

import com.sena.dummy.domain.model.ProductModel
import com.sena.dummy.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: ProductModel): ProductModel =
        repository.updateProduct(product)
}