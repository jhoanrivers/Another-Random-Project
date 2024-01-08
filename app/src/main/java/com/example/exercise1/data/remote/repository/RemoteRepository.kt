package com.example.exercise1.data.remote.repository

import com.example.exercise1.data.remote.apiservice.ApiResult
import com.example.exercise1.data.remote.apiservice.ApiService
import com.example.exercise1.data.remote.model.RandomDog
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteRepository{

    fun getRandomDog(): Flow<ApiResult<RandomDog>>

}