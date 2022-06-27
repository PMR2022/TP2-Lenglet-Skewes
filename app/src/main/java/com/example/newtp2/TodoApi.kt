package com.example.newtp2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TodoApi {

    @Headers("hash: f865dd524acf6d8edc8341cd9956af41")
    @GET("/users")


}