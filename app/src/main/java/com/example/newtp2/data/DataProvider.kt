package com.example.newtp2.data


import com.example.newtp2.ItemResponse
import com.example.newtp2.TodoListResponse
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

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun modifyBaseUrl(newUrl : String){
        baseUrl = newUrl
    }

    val Lists = retrofit.create<Todos>()
    val getItems = retrofit.create<GetItems>()
    val checkedItem = retrofit.create<ModifyChecked>()

    suspend fun getListes(hash : String) : TodoListResponse? = Lists.getTodos(hash).body()
    suspend fun getTheItems(id : Int, hash : String) : ItemResponse? = getItems.getItems(id, hash).body()
    suspend fun checkedItem(idList : Int, idItem : Int, value : Int, hash : String) = checkedItem.changeChecked(idList, idItem, value, hash)
    suspend fun postItem(idList : Int, label : String, url: String = "url test", hash : String) = getItems.postItem(idList, label, url, hash)

}