import com.br.contactfetchapp.data.repository.RandomUserRepository
import com.br.contactfetchapp.domain.usecase.RandomUserUseCase
import com.br.contactfetchapp.domain.usecase.RandomUserUseCaseImpl
import com.br.contactfetchapp.factory.RandomUserFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomUserUseCaseImplTest {

    @Test
    fun `test get random user list`() = runBlocking {
        val mockRepository = mockk<RandomUserRepository>()
        val useCase: RandomUserUseCase = RandomUserUseCaseImpl(mockRepository)
        val mockUserList = listOf(
            RandomUserFactory.make(name = "Jaylen Brown"),
            RandomUserFactory.make(name = "Jayson Tatum")
        )
        coEvery { mockRepository.getAllUser(any(), any()) } returns flow { emit(mockUserList) }

        val result = useCase(1, 10).toList()

        assertEquals(mockUserList, result.first())
    }
}
