package com.ishanvohra.skylarktask.ui.activities.mainActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import com.ishanvohra.skylarktask.models.GetUserDetailsResponse
import com.ishanvohra.skylarktask.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    val baseUrl = "https://api.github.com/"
    private val TAG = javaClass.simpleName

    fun getUserDetails(userName: String) : MutableLiveData<GetUserDetailsResponse>{
        val mutableLiveData = MutableLiveData<GetUserDetailsResponse>()

        RetrofitClient(baseUrl).instance.getUserDetails(userName).enqueue(object : Callback<GetUserDetailsResponse>{
            override fun onResponse(
                call: Call<GetUserDetailsResponse>,
                response: Response<GetUserDetailsResponse>
            ) {
                if(response.isSuccessful){
                    mutableLiveData.value = response.body()
                }

                Log.d(TAG, "getUserDetails: ${response.code()} ${response.message()}")
            }

            override fun onFailure(call: Call<GetUserDetailsResponse>, t: Throwable) {
                Log.d(TAG, "getUserDetails failed: ${t.message}")

            }

        })

        return mutableLiveData
    }

    fun getRepositories(userName: String) : MutableLiveData<List<GetRepositoryListResponseItem>>{
        val mutableLiveData = MutableLiveData<List<GetRepositoryListResponseItem>>()

        RetrofitClient(baseUrl).instance.getRepositories(userName).enqueue(object : Callback<List<GetRepositoryListResponseItem>>{
            override fun onResponse(
                call: Call<List<GetRepositoryListResponseItem>>,
                response: Response<List<GetRepositoryListResponseItem>>
            ) {
                if(response.isSuccessful){
                    mutableLiveData.value = response.body()
                }

                Log.d(TAG, "getUserDetails: ${response.code()} ${response.message()}")
            }

            override fun onFailure(call: Call<List<GetRepositoryListResponseItem>>, t: Throwable) {
                Log.d(TAG, "getUserDetails failed: ${t.message}")

            }

        })

        return mutableLiveData
    }

}