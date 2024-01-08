package com.example.exercise1.data.remote.apiservice

import com.example.exercise1.data.remote.model.RandomDog
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("breeds/image/random")
    suspend fun getRandomDog() : Response<RandomDog>
}