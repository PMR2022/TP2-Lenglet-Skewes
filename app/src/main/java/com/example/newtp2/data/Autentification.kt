package com.example.newtp2.data

import android.content.Context
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Autentification {
    @POST("authenticate")
    @FormUrlEncoded
    suspend fun autentification(
        @Field("user") user: String, //pour passer le nom de l'utilisateur qu'on entrera
        @Field("password") password: String // idem pour le mdp
        //@Header("hash") hash: String
    ) : Response<String>
}