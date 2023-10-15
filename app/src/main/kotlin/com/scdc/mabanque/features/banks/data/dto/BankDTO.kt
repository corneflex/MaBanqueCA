package com.scdc.mabanque.features.banks.data.dto

data class BankDTO(
    val accounts: List<AccountDTO>,
    val isCA: Int,
    val name: String
)