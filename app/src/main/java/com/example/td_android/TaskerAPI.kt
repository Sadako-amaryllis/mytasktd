package com.example.td_android

import retrofit2.Call
import retrofit2.http.GET

interface TaskerAPI {
    @GET("api/tasks")
    fun getTasks(): Call<ApiResponse>

}