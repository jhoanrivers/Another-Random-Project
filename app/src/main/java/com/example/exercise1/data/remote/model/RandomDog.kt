package com.example.exercise1.data.remote.model

import com.google.gson.annotations.SerializedName

data class RandomDog(

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String

)