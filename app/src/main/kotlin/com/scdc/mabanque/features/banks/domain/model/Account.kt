package com.scdc.mabanque.features.banks.domain.model

data class Account(
    val balance: Double,
    val contractNumber: String,
    val holder: String,
    val id: String,
    val label: String,
    val operations: List<Operation>,
    val order: Int,
    val productCode: String,
    val role: Int
)