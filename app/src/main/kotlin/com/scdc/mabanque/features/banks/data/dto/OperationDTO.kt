package com.scdc.mabanque.features.banks.data.dto

import com.squareup.moshi.Json
import java.util.Date

data class OperationDTO(
    val amount: String,
    val category: String,
    val date: Date,
    val id: String,
    val title: String
)