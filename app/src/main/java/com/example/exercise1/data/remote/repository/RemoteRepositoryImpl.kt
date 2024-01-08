package com.example.exercise1.data.remote.repository

import com.example.exercise1.data.remote.apiservice.ApiResult
import com.example.exercise1.data.remote.apiservice.ApiService
import com.example.exercise1.data.remote.model.RandomDog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Response
import java.util.Random
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) : RemoteRepository {

    override fun getRandomDog() : Flow<ApiResult<RandomDog>> {
        return flow {

            try {
                val response = apiService.getRandomDog()

                if(response.isSuccessful){
                    emit(ApiResult.SuccessResult(response.body()))
                } else{
                    throw Exception("Failed load random dog")
                }
            } catch (e: Exception) {
                emit(ApiResult.ErrorResult(e.message))
            }



        }.flowOn(Dispatchers.IO)




    }
}