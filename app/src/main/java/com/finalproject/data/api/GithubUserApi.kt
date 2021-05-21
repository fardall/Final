package com.finalproject.data.api

import com.finalproject.data.api.response.SearchResponse
import com.finalproject.data.api.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserApi {
    @GET("search/users")
    fun searchUser(
        @Query("q") username: String,
        @Header("Authorization") token: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") username: String, @Header("Authorization") token: String
    ): Call<UserItem>
}