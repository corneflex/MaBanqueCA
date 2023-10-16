package com.scdc.mabanque.features.banks.domain.repository

import com.scdc.mabanque.features.banks.domain.model.Bank

interface BankDataSource {
    suspend fun fetchBanks(): List<Bank>
}