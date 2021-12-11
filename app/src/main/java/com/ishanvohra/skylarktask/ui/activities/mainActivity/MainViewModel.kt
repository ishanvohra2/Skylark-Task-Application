package com.ishanvohra.skylarktask.ui.activities.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import com.ishanvohra.skylarktask.models.GetUserDetailsResponse

class MainViewModel : ViewModel() {

    fun getUserDetails(userName: String) : LiveData<GetUserDetailsResponse>{
        return MainRepository().getUserDetails(userName)
    }

    fun getRepositories(userName: String) : LiveData<List<GetRepositoryListResponseItem>>{
        return MainRepository().getRepositories(userName)
    }
}