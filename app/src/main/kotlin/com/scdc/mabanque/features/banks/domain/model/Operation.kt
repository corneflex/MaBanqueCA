package com.scdc.mabanque.features.banks.domain.model

import java.util.Date

data class Operation(
    val amount: String,
    val category: String,
    val date: Date,
    val id: String,
    val title: String
)