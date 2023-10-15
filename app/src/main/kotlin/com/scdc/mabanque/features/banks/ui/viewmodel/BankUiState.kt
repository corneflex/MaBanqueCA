package com.scdc.mabanque.features.banks.ui.viewmodel

import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.domain.model.Bank

data class BankUiState(
    val caBanks: List<Bank> = emptyList(),
    val otherBanks: List<Bank> = emptyList(),
    // Map of accounts of All Banks supposing that account id is unique across bank
    val accounts: Map<String,Account> = emptyMap<String,Account>(),
    val isLoading: Boolean = false
)

