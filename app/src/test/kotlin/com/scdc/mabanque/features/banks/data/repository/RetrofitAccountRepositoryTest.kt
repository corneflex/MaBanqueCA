import com.scdc.mabanque.features.banks.data.dto.bankDTO
import com.scdc.mabanque.features.banks.data.remote.BankService
import com.scdc.mabanque.features.banks.data.repository.RetrofitAccountRepository
import com.scdc.mabanque.features.banks.domain.model.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RetrofitAccountRepositoryTest {

    private lateinit var repository: RetrofitAccountRepository
    private val bankService: BankService = mockk()

    @Before
    fun setup() {
        repository = RetrofitAccountRepository(bankService)
    }

    @Test
    fun `getBankAccounts emits Success when API call is successful`() = runBlocking {
       runTest {
           val mockData = listOf(bankDTO)
           coEvery { bankService.getBanks() } returns mockData

           val result = repository.getBankAccounts().toList()

           assertTrue(result[0] is Resource.Loading)
           assertTrue(result[1] is Resource.Success)
       }
    }

    @Test
    fun `getBankAccounts emits Error when API call fails`() = runBlocking {
        runTest {
            val exception = Exception("Network Error")
            coEvery { bankService.getBanks() } throws exception

            val result = repository.getBankAccounts().toList()
            assertTrue(result[0] is Resource.Loading)
            assertTrue(result[1] is Resource.Error)
            assertEquals(exception, (result[1] as Resource.Error).error)
        }
    }
}
