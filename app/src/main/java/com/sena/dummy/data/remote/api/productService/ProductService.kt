package com.sena.dummy.data.remote.api.productService

import com.sena.dummy.data.remote.dto.Product.ProductListResponse
import com.sena.dummy.data.remote.dto.Product.ProductX
import com.sena.dummy.data.remote.dto.Product.UpdateProductRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductService {
    @GET("products")
    suspend fun getProducts(): ProductListResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductX

    @DELETE("products/{id}")
    suspend fun deleteProductById(
        @Path("id") id: Int
    ): ProductX

    @PATCH("products/{id}")
    suspend fun updateProductPartial(
        @Path("id") id: Int,
        @Body product: UpdateProductRequest
    ): ProductX

    @PUT("products/{id}")
    suspend fun updateProductById(
        @Path("id") id: Int,
        @Body product: UpdateProductRequest
    ): ProductX

    @POST("products/add")
    suspend fun addProduct(
        @Body product: UpdateProductRequest
    ): ProductX
}