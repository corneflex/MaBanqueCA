package com.scdc.mabanque.features.banks.data.remote

import com.scdc.mabanque.features.banks.data.mapper.toDomain
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.repository.BankDataSource

class RemoteBankDataSource(private val accountService: BankService) : BankDataSource {
    override suspend fun fetchBanks(): List<Bank> {
        return accountService.getBanks().map { it.toDomain() }
    }
}