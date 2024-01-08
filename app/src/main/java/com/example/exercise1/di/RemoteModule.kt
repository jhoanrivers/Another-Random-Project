package com.example.exercise1.di

import com.example.exercise1.data.remote.apiservice.ApiService
import com.example.exercise1.data.remote.apiservice.ExchangeService
import com.example.exercise1.data.remote.repository.RemoteRepository
import com.example.exercise1.data.remote.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.internal.connection.Exchange
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    val baseURL = "https://dog.ceo/api/"
    val currencyBaseUrl = "https://currency-exchange.p.rapidapi.com/"



    @Provides
    @Singleton
    fun provideHttpLogging() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    @Named("firstRetrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(@Named("firstRetrofit") retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) : RemoteRepository {
        return RemoteRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    @Named("secondRetrofit")
    fun provideExchangeRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(currencyBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideExchangeService(@Named("secondRetrofit") retrofit: Retrofit) : ExchangeService {
        return retrofit.create(ExchangeService::class.java)
    }


}