package com.sena.dummy.domain.useCase

import com.sena.dummy.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Boolean =
        repository.deleteProduct(id)
}