package com.example.exercise1.pages.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise1.data.remote.apiservice.ApiResult
import com.example.exercise1.data.remote.model.RandomDog
import com.example.exercise1.data.remote.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val remoteRepository: RemoteRepository) : ViewModel() {

    val randomDogData = MutableLiveData<RandomDog>()
    val errorData = MutableLiveData<String>()
    val loadingData = MutableLiveData<Boolean>()


    fun getRandomDog() {

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                loadingData.postValue(true)
            }
            try {
                remoteRepository.getRandomDog().collect{
                    when(it){
                        is ApiResult.ErrorResult -> errorData.postValue(it.errorMessage.orEmpty())
                        is ApiResult.SuccessResult -> randomDogData.postValue(it.data!!)
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    e.printStackTrace()
                    loadingData.postValue(false)
                    errorData.postValue(e.message)
                }
            }
        }


    }




}