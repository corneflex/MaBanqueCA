package com.scdc.mabanque.features.banks.data.dto

import com.squareup.moshi.Json

data class AccountDTO(
    val balance: Double,
    @Json(name= "contract_number") val contractNumber: String,
    val holder: String,
    val id: String,
    val label: String,
    val operations: List<OperationDTO>,
    val order: Int,
    @Json(name= "product_code") val productCode: String,
    val role: Int
)