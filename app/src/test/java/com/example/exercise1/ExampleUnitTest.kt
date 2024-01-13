package com.example.exercise1

import com.example.exercise1.data.remote.apiservice.ApiResult
import com.example.exercise1.data.remote.apiservice.ApiService
import com.example.exercise1.data.remote.model.RandomDog
import com.example.exercise1.data.remote.repository.RemoteRepository
import com.example.exercise1.data.remote.repository.RemoteRepositoryImpl
import com.example.exercise1.pages.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.Random

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

    lateinit var testViewModel: MainViewModel

    @Before
    fun setup() {
        remoteRepository = RemoteRepositoryImpl(apiService)
        testViewModel = MainViewModel(remoteRepository)

    }


    @Test
    fun testRandomDog()  = runBlocking{


        val someDog = RandomDog("someDog", "success")

        val mockedResponse : Response<RandomDog> = Response.success(RandomDog("someDog", "success"))
        lenient().`when`(apiService.getRandomDog()).thenReturn(mockedResponse)

        assertEquals(mockedResponse.body(), someDog)
    }

    @Test
    fun testRepository() {


        val mockResponse : Flow<ApiResult<RandomDog>> = flowOf(ApiResult.SuccessResult(RandomDog("someDog", "success")))
        `when`(remoteRepository.getRandomDog()).thenReturn(mockResponse)


        assertEquals(2 + 2, 4)
    }

}