package com.finalproject.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalproject.BuildConfig
import com.finalproject.data.api.GithubUserRepository
import com.finalproject.data.api.response.SearchResponse
import com.finalproject.data.api.response.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserViewModel : ViewModel() {

    private val listSearch = MutableLiveData<List<UserItem>>()
    private val detailUser = MutableLiveData<UserItem>()

    private val token = "token " + BuildConfig.GITHUB_TOKEN

    private lateinit var repository: GithubUserRepository

    fun initRepository(context: Context) {
        repository = GithubUserRepository.getInstance(context)
    }

    fun searchUser(username: String) {
        repository.searchUser(username,token).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                response.body()?.let {
                    listSearch.value = it.list_user
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }

    fun detailUser(username: String) {
        repository.getDetail(username, token).enqueue(object : Callback<UserItem> {
            override fun onResponse(
                call: Call<UserItem>,
                response: Response<UserItem>
            ) {
                response.body()?.let {
                    detailUser.value = it
                }
            }

            override fun onFailure(call: Call<UserItem>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    fun getListSearch(): LiveData<List<UserItem>>{
        return listSearch
    }

    fun getDetail(): LiveData<UserItem> {
        return detailUser
    }
}