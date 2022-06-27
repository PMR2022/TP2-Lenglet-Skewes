package com.example.newtp2.data


import com.google.gson.Gson
//import fr.ec.sequence1.data.api.ProductHuntService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object DataProvider {
    private var baseUrl =
        "http://tomnab.fr/todo-api/"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun modifyBaseUrl(newUrl : String){
        baseUrl = newUrl
    }



}