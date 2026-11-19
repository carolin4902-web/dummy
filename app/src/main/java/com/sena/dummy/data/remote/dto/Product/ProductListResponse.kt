package com.sena.dummy.data.remote.dto.Product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListResponse(
    val products: List<ProductX> = emptyList(),
)
