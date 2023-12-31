
import com.scdc.mabanque.features.banks.data.dto.bankDTO
import com.scdc.mabanque.features.banks.data.repository.RetrofitAccountRepository
import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.repository.BankDataSource
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
    private val bankDataSource: BankDataSource = mockk()

    @Before
    fun setup() {
        repository = RetrofitAccountRepository(bankDataSource)
    }

    @Test
    fun `getBankAccounts emits Success when API call is successful`() = runBlocking {
       runTest {
           val mockData = listOf(bankDTO)
           coEvery { bankDataSource.fetchBanks() } returns mockData

           val result = repository.getBankAccounts().toList()

           assertTrue(result[0] is Resource.Loading)
           assertTrue(result[1] is Resource.Success)
       }
    }

    @Test
    fun `getBankAccounts emits Error when API call fails`() = runBlocking {
        runTest {
            val exception = Exception("Network Error")
            coEvery { bankDataSource.fetchBanks() } throws exception

            val result = repository.getBankAccounts().toList()
            assertTrue(result[0] is Resource.Loading)
            assertTrue(result[1] is Resource.Error)
            assertEquals(exception, (result[1] as Resource.Error).error)
        }
    }
}
