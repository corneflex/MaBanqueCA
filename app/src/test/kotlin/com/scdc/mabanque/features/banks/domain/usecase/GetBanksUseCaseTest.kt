package com.scdc.mabanque.features.banks.domain.usecase

import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.repository.BankRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetBanksUseCaseTest {

    @MockK
    lateinit var repository: BankRepository


    private lateinit var getBanksUseCase: GetBanksUseCase


   @Before
   fun setUp(){
       MockKAnnotations.init(this)
       getBanksUseCase = GetBanksUseCase(repository::getBankAccounts)
   }

    @Test
    fun `when repo returns success then resource returned should be success`() {
        mockSuccess()
        runTest {
            val getBanksUseCase = getBanksUseCase()

            val eventCount = getBanksUseCase.count()
            assert(eventCount == 2)

            var resource = getBanksUseCase.first()
            assert(resource is Resource.Loading)

            resource = getBanksUseCase.last()
            assert(resource is Resource.Success)
            println(resource)
        }
    }

    @Test
    fun `Get Banks List, correct movie list return` (): Unit = runBlocking{
        mockError()
        runTest {
            val getBanksUseCase = getBanksUseCase()

            val eventCount = getBanksUseCase.count()
            assert(eventCount == 2)

            var resource = getBanksUseCase.first()
            assert(resource is Resource.Loading)

            resource = getBanksUseCase.last()
            assert(resource is Resource.Error)
            println(resource)
        }

    }

    private fun mockSuccess() {
        coEvery { repository.getBankAccounts() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(listOf(Bank(isCA = 1, name = "Credit Agricole", accounts = emptyList()))))
        }
    }

    private fun mockError() {
        coEvery { repository.getBankAccounts() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error(Throwable()))
        }
    }



}