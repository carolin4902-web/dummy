package com.sena.dummy.domain.repository

import com.sena.dummy.domain.model.ProductModel

// estos son los contratos //

interface ProductRepository {
    suspend fun getProducts(): List<ProductModel>

    suspend fun getProductById(id: Int): ProductModel

    suspend fun createProduct(product: ProductModel): ProductModel

    suspend fun updateProduct(product: ProductModel): ProductModel

    suspend fun deleteProduct(id: Int): Boolean
}

//estructura que el repo principal debe respetar//