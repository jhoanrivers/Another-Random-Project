package com.example.exercise1.data.remote.apiservice

import com.example.exercise1.data.remote.model.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface ExchangeService {

    @GET("/listquotes")
    @Headers("X-RapidAPI-Key:3b194c174dmsh3ea0edfd17e78cep126a2bjsnc0a88c50cc20", "X-RapidAPI-Host:currency-exchange.p.rapidapi.com" )
    fun getListQuotes() : Response<List<Currency>>
}