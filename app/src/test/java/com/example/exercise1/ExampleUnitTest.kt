package com.example.exercise1

import com.example.exercise1.data.remote.apiservice.ApiService
import com.example.exercise1.data.remote.model.RandomDog
import com.example.exercise1.data.remote.repository.RemoteRepository
import com.example.exercise1.data.remote.repository.RemoteRepositoryImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {


    @Mock
    lateinit var apiService: ApiService

    lateinit var remoteRepository: RemoteRepository



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteRepository = RemoteRepositoryImpl(apiService)
    }


    @Test
    fun testRandomDog()  = runBlockingTest{

        val mockedResponse : Response<RandomDog> = Response.success(RandomDog("someDog", "success"))


        `when`(apiService.getRandomDog()).thenReturn(mockedResponse)

        val result  = remoteRepository.getRandomDog()

        assertEquals(mockedResponse, result)
    }

}