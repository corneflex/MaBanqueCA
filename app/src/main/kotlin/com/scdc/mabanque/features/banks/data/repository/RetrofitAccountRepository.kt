package com.scdc.mabanque.features.banks.data.repository

import com.scdc.mabanque.features.banks.data.mapper.toDomain
import com.scdc.mabanque.features.banks.data.remote.BankService
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RetrofitAccountRepository(private val accountService: BankService) : BankRepository {
    override suspend fun getBankAccounts(): Flow<Resource<List<Bank>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(accountService.getBanks().map { it.toDomain() }))
        } catch (e: Exception) {
            println("Exception: $e")
            emit(Resource.Error(e))
        }
    }
}