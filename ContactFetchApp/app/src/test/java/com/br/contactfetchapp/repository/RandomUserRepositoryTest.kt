package com.br.contactfetchapp.repository

import android.net.ConnectivityManager
import com.br.contactfetchapp.data.model.RandomUser
import com.br.contactfetchapp.data.repository.RandomUserRepositoryImpl
import com.br.contactfetchapp.data.source.local.room.dao.RandomUserDao
import com.br.contactfetchapp.data.source.remote.api.RandomUserApi
import com.br.contactfetchapp.factory.RandomUserFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RandomUserRepositoryImplTest {

    @Mock
    private lateinit var mockApi: RandomUserApi

    @Mock
    private lateinit var mockDao: RandomUserDao

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    private lateinit var repository: RandomUserRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = RandomUserRepositoryImpl(mockApi, mockDao, mockConnectivityManager)
    }


    @Test
    fun `test get all users from local`() = runBlocking {
        val mockUserList = listOf(
            RandomUserFactory.makeEntity(name = "Jaylen Brown"),
            RandomUserFactory.makeEntity(name = "Jayson Tatum")
        )
        `when`(mockDao.getAllUsers()).thenReturn(flowOf(mockUserList))

        val result: Flow<List<RandomUser>> = repository.getAllUser(page = 1, pageSize = 10)

        assertEquals(mockUserList, result.single())
    }
}
