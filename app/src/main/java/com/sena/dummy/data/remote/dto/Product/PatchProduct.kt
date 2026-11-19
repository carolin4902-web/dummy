package com.sena.dummy.data.remote.dto.Product

import com.squareup.moshi.Json

data class patchProduct(
    @param:Json(name = "category")
    val category: String,
)