package com.sena.dummy.data.remote.dto.Product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductX(
    @param:Json(name = "availabilityStatus")
    val availabilityStatus: String? = null,
    @param:Json(name = "brand")
    val brand: String? = null,
    @param:Json(name = "category")
    val category: String,
    @param:Json(name = "description")
    val description: String,
    @param:Json(name = "dimensions")
    val dimensions: Dimensions? = null,
    @param:Json(name = "discountPercentage")
    val discountPercentage: Double = 0.0,
    @param:Json(name = "id")
    val id: Int,
    @param:Json(name = "images")
    val images: List<String> = emptyList(),
    @param:Json(name = "meta")
    val meta: Meta? = null,
    @param:Json(name = "minimumOrderQuantity")
    val minimumOrderQuantity: Int = 1,
    @param:Json(name = "price")
    val price: Double,
    @param:Json(name = "rating")
    val rating: Double = 0.0,
    @param:Json(name = "returnPolicy")
    val returnPolicy: String? = null,
    @param:Json(name = "reviews")
    val reviews: List<Review> = emptyList(),
    @param:Json(name = "shippingInformation")
    val shippingInformation: String? = null,
    @param:Json(name = "sku")
    val sku: String? = null,
    @param:Json(name = "stock")
    val stock: Int = 0,
    @param:Json(name = "tags")
    val tags: List<String> = emptyList(),
    @param:Json(name = "thumbnail")
    val thumbnail: String? = null,
    @param:Json(name = "title")
    val title: String,
    @param:Json(name = "warrantyInformation")
    val warrantyInformation: String? = null,
    @param:Json(name = "weight")
    val weight: Int = 0
)