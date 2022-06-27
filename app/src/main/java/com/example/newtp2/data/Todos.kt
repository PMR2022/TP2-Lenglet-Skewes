package com.example.newtp2.data


import com.example.newtp2.TodoListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.Response
import retrofit2.http.*

interface Todos {
    @GET("lists")
    suspend fun getTodos(
        @Header("hash") hash: String
    ) : Response<TodoListResponse>
}
