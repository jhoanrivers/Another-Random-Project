package com.example.exercise1.data.remote.apiservice

import com.example.exercise1.data.remote.model.RandomDog

sealed class ApiResult<out T> {
    class SuccessResult<out T>(val data : RandomDog? = null) : ApiResult<T>()
    class ErrorResult(val errorMessage: String? = null) : ApiResult<Nothing>()

}
