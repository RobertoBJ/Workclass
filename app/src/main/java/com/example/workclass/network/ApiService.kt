package com.example.workclass.network

import com.example.workclass.data.model.AccountModel
import com.example.workclass.data.model.UserModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/user")
    suspend fun login (@Body user:UserModel):Response<JsonObject>

    @GET("cuenta")
    suspend fun getAccounts(): Response<List<AccountModel>>

    @GET("cuenta/{id}")
    suspend fun getAccount(@Path("id")id:Int):Response<AccountModel>

    @POST("cuenta")
    suspend fun addAccount(@Body service: AccountModel):Response<JsonObject>

    @PUT("cuenta/{id}")
    suspend fun updateAccount(@Path("id") id:Int, @Body service:AccountModel): Response<JsonObject>

    @DELETE("cuenta/{id}")
    suspend fun deleteAccount(@Path("id") id:Int): Response<JsonObject>

}