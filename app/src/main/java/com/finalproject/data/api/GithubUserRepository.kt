package com.finalproject.data.api

import android.content.Context
import com.finalproject.data.api.response.SearchResponse
import com.finalproject.data.api.response.UserItem
import retrofit2.Call

class GithubUserRepository : GithubUserApi {

    companion object{
        private lateinit var githubUserApi: GithubUserApi

        fun getInstance(context: Context) : GithubUserRepository{
            githubUserApi = RetrofitService.createService(GithubUserApi::class.java, OkHttpClientService.create(context))
            return GithubUserRepository()
        }
    }

    override fun searchUser(username: String, token: String): Call<SearchResponse> {
        return githubUserApi.searchUser(username,token)
    }

    override fun getDetail(username: String, token: String): Call<UserItem> {
        return githubUserApi.getDetail(username, token)
    }
}