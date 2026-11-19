package com.sena.dummy.data.mapper

import com.sena.dummy.data.remote.dto.Product.ProductX
import com.sena.dummy.domain.model.ProductModel

fun ProductX.toDomain(): ProductModel = ProductModel(
    id = id,
    title = title,
    description = description,
    category = category,
    price = price,
    imageUrl = thumbnail.orEmpty().ifBlank { images.orEmpty().firstOrNull().orEmpty() }
)