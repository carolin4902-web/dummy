package com.sena.dummy.data.remote.dto.Product


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @param:Json(name = "barcode")
    val barcode: String,
    @param:Json(name = "createdAt")
    val createdAt: String,
    @param:Json(name = "qrCode")
    val qrCode: String,
    @param:Json(name = "updatedAt")
    val updatedAt: String
)