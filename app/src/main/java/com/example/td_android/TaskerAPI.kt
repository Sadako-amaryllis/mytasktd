package com.example.td_android

import com.example.td_android.ui.TaskForm
import retrofit2.Call
import retrofit2.http.*

const val API_URL = "http://10.0.2.2:1337/api/"
const val TAG = "CHECK_RESPONSE"

interface TaskerAPI {

    @GET("tasks")
    fun getTasks(): Call<ApiResponse>

    @POST("tasks")
    fun addTask(@Body task: Tasks): Call<Tasks>
}