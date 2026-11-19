package com.sena.dummy.data.repository

import com.sena.dummy.data.mapper.toDomain
import com.sena.dummy.data.remote.api.productService.ProductService
import com.sena.dummy.data.remote.dto.Product.UpdateProductRequest
import com.sena.dummy.domain.model.ProductModel
import com.sena.dummy.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductService
) : ProductRepository {

    override suspend fun getProducts(): List<ProductModel> =
        api.getProducts().products.map { it.toDomain() }

    override suspend fun getProductById(id: Int): ProductModel =
        api.getProductById(id).toDomain()

    override suspend fun createProduct(product: ProductModel): ProductModel =
        api.addProduct(product.toRequest()).toDomain()

    override suspend fun updateProduct(product: ProductModel): ProductModel =
        api.updateProductById(product.id, product.toRequest()).toDomain()

    override suspend fun deleteProduct(id: Int): Boolean {
        api.deleteProductById(id)
        return true
    }

    private fun ProductModel.toRequest() = UpdateProductRequest(
        title = title,
        description = description,
        category = category,
        price = price
    )
}