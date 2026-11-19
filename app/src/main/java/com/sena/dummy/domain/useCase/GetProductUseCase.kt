package com.sena.dummy.domain.useCase

import com.sena.dummy.domain.model.ProductModel
import com.sena.dummy.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(): List<ProductModel> = repository.getProducts()
    suspend fun byId(id: Int): ProductModel = repository.getProductById(id)
}

// invoke es una palabra reservada, automaticamente nos da lo que pedimos
// invetigar el invoke, para tener mejor claridad
// casos de uso, que son?, como se usan?, relacion con el usuario