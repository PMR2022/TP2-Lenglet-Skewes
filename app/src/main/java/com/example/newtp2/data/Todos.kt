package com.example.newtp2.data


import retrofit2.http.GET
import retrofit2.http.Header
import com.example.newtp2.TodoResponse
import retrofit2.Response
import retrofit2.http.*

interface Todos {
    @GET("lists")
    suspend fun getTodos(
        @Header("hash") hash: String
    ) : Response<TodoResponse>
}
