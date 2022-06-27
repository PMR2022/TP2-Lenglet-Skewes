package com.example.newtp2.data

import com.example.newtp2.ItemResponse

import retrofit2.Response
import retrofit2.http.*

interface GetItems {
    @GET("lists/{id_list}/items")
    suspend fun getItems(
        @Path(value = "id_list", encoded = true) id : Int,
        @Header("hash") hash: String
    ) : Response<ItemResponse>

    @POST("lists/{id_list}/items")
    suspend fun postItem(
        @Path(value = "id_list", encoded = true) idList : Int, // pour spécifier l'id de la list dont on veut les items
        @Query(value = "label", encoded = true) label : String,
        @Query(value = "url", encoded = true) url : String,
        @Header("hash") hash: String,
    ) : Response<ItemResponse>
}

interface ModifyChecked {
    @PUT("lists/{id_list}/items/{id_item}")
    suspend fun changeChecked(
        @Path(value = "id_list", encoded = true) idList : Int,
        @Path(value = "id_item", encoded = true) idItem : Int,
        @Query(value = "check", encoded = true) value: Int,
        @Header("hash") hash: String
    ) : Response<Any>
}