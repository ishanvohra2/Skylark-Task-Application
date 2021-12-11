package com.ishanvohra.skylarktask.network

import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import com.ishanvohra.skylarktask.models.GetUserDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//interface containing all the API Calls

interface Api  {

    @GET("users/{user_name}")
    fun getUserDetails(@Path("user_name")userName: String) : Call<GetUserDetailsResponse>

    @GET("users/{user_name}/repos")
    fun getRepositories(@Path("user_name")userName: String) : Call<List<GetRepositoryListResponseItem>>
}