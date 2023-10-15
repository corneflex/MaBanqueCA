package com.scdc.mabanque.features.banks.ui.viewmodel

import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.model.caBankA
import com.scdc.mabanque.features.banks.domain.model.caBankB
import com.scdc.mabanque.features.banks.domain.model.operationA
import com.scdc.mabanque.features.banks.domain.model.operationB
import com.scdc.mabanque.features.banks.domain.model.operationC
import com.scdc.mabanque.features.banks.domain.model.otherBankA
import com.scdc.mabanque.features.banks.domain.model.otherBankB
import com.scdc.mabanque.features.banks.domain.usecase.GetBanksUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class BankViewModelTest {
    @MockK
    lateinit var getBanksUseCase: GetBanksUseCase

    private lateinit var bankViewModel: BankViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when use case returns success then it should return a value`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Success(listOf(caBankA))) }
            bankViewModel = BankViewModel(getBanksUseCase)
            coVerify(exactly = 1) { getBanksUseCase() }
            assert(bankViewModel.state.value.caBanks.count() == 1)
        }
    }

    @Test
    fun `when use case returns error then resource returned should be error`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Error(Throwable("error"))) }
            bankViewModel = BankViewModel(getBanksUseCase)
            coVerify(exactly = 1) { getBanksUseCase() }

            assert(bankViewModel.failure.value.error != null)
        }
    }

    @Test
    fun `it should add a CA Bank but not an other Bank`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Success(listOf(caBankA))) }
            bankViewModel = BankViewModel(getBanksUseCase)
            coVerify(exactly = 1) { getBanksUseCase() }

            assert(bankViewModel.state.value.caBanks.count() == 1)
            assert(bankViewModel.state.value.otherBanks.isEmpty())
        }
    }

    @Test
    fun `it should add an other Bank but not a CA Bank`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Success(listOf(otherBankB))) }
            bankViewModel = BankViewModel(getBanksUseCase)
            coVerify(exactly = 1) { getBanksUseCase() }

            assert(bankViewModel.state.value.caBanks.isEmpty())
            assert(bankViewModel.state.value.otherBanks.count() == 1)
        }
    }

    @Test
    fun `it should add a CA Bank and an other Bank`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow {
                emit(
                    Resource.Success(
                        listOf(
                            caBankA,
                            otherBankB
                        )
                    )
                )
            }
            bankViewModel = BankViewModel(getBanksUseCase)
            coVerify(exactly = 1) { getBanksUseCase() }

            assert(bankViewModel.state.value.caBanks.count() == 1)
            assert(bankViewModel.state.value.otherBanks.count() == 1)
        }
    }

    @Test
    fun `it should add an Other Bank`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Success(listOf(otherBankB))) }
            bankViewModel = BankViewModel(getBanksUseCase)

            coVerify(exactly = 1) { getBanksUseCase() }

            assert(bankViewModel.state.value.otherBanks.count() == 1)
        }
    }

    @Test
    fun `it should return Banks sorted by alphabetic order`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow {
                emit(
                    Resource.Success(
                        listOf(
                            otherBankB,
                            caBankB, caBankA, otherBankA
                        )
                    )
                )
            }
            bankViewModel = BankViewModel(getBanksUseCase)

            coVerify(exactly = 1) { getBanksUseCase() }

            assertThat(bankViewModel.state.value.otherBanks, `is`(listOf(otherBankA, otherBankB)))
            assertThat(bankViewModel.state.value.caBanks, `is`(listOf(caBankA, caBankB)))
        }
    }

    @Test
    fun `it should return Accounts operations sorted by date or alphabetic order if they have the same date`() {
        runTest {
            coEvery { getBanksUseCase() } returns flow { emit(Resource.Success(listOf(caBankA))) }
            bankViewModel = BankViewModel(getBanksUseCase)

            coVerify(exactly = 1) { getBanksUseCase() }

            // Operations for account A of caBankA have the following order  operationB, operationA, operationC
            // operationA and operationB has the same date and are ordered by Alphabetic Order
            // operationC is the latest operation
            // FYI: these tests are quite complicated they should have been simplified with the creation of a ViewModel for the accounts
            // instead of managing Bank accounts and account detail view with the same ViewModel

            assertThat(
                bankViewModel.state.value.accounts["A"]?.operations,
                `is`(listOf(operationC, operationA, operationB))
            )

        }
    }
}

