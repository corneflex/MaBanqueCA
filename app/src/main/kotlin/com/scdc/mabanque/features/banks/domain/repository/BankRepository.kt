package com.scdc.mabanque.features.banks.domain.repository

import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.model.Bank
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    suspend fun getBankAccounts(): Flow<Resource<List<Bank>>>
}