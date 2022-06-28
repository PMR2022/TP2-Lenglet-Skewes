package com.example.newtp2.data


import android.app.Application
import android.util.Log
import com.example.newtp2.ItemResponse
import com.example.newtp2.TodoList
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

class DataProvider(app: Application) {
    private var baseUrl =
        "http://tomnab.fr/todo-api/"




    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val Lists = retrofit.create<Todos>()
    val getItems = retrofit.create<GetItems>()
    val checkedItem = retrofit.create<ModifyChecked>()
    val authenticate = retrofit.create<Autentification>()

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun modifyBaseUrl(newUrl : String){
        baseUrl = newUrl
    }

    suspend fun getListes(hash: String, name: String) : List<TodoList> {
        // Avoir tout les listes d'un utilisateur.
        // On donne le nom pour chercher dans la bd ou le hash pour l'API
        return try {
            Log.d("State", "Api: getListes")
            val lists = Lists.getTodos(hash).body()?.lists!!
            /*val id = getUser(name, hash)
            for (list in lists) {
                list.idUser = id!!.id
            }*/
            //listDao.saveOrUpdateList(lists)
            lists
        } catch (exception : Exception) {
            mutableListOf<TodoList>()
        }
    }
/*
    suspend fun authenticate(user : String, password : String) : Any {
        return try {
            var hash = authenticate.autentification(user, password)
            var user = getUser(user, hash!!.hash_text)
            Log.d("User", "User found: ")
            Log.d("User", "User found: $user")
            if (user != null) {
                listDao.saveOrUpdateUser(user)
            }
            hash
        } catch (exception : Exception) {
        }
    }

    suspend fun getUser(name : String, hash : String) : Pseudo? {
        // Permet d'obtenir le user par son name, et son hash
        // Mode online on retrouve avec l'api
        // Mode offline avec la bd
        return try {
            var userReturn : Pseudo? = null
            val users : List<Pseudo> = authenticate.getUsers(hash).body()!!.users
            Log.d("Users", "API getUser: $users")
            for (user in users) {
                Log.d("User", "Checking user :  ${user.name} for $name")
                if (user.name == name) {
                    Log.d("User", "User found! $name")
                    userReturn = user
                }
            }
            userReturn
        } catch (exception : Exception) {

        }
    }
*/

    suspend fun getListes(hash : String) : TodoListResponse? = Lists.getTodos(hash).body()
    suspend fun getTheItems(id : Int, hash : String) : ItemResponse? = getItems.getItems(id, hash).body()
    suspend fun checkedItem(idList : Int, idItem : Int, value : Int, hash : String) = checkedItem.changeChecked(idList, idItem, value, hash)
    suspend fun postItem(idList : Int, label : String, url: String = "url test", hash : String) = getItems.postItem(idList, label, url, hash)

}