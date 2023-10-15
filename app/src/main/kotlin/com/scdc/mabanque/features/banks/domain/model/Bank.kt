package com.scdc.mabanque.features.banks.domain.model

import kotlin.math.roundToInt

data class Bank(
    val accounts: List<Account>,
    val isCA: Int,
    val name: String
) {
    val totalBalance: Double by lazy {
        (accounts.sumOf { it.balance } * 100).roundToInt() / 100.0
    }
}