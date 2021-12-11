package com.ishanvohra.skylarktask.ui.activities.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishanvohra.skylarktask.R
import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import com.ishanvohra.skylarktask.models.GetUserDetailsResponse
import com.ishanvohra.skylarktask.ui.activities.viewRepoDetails.ViewRepositoryDetailsActivity
import com.ishanvohra.skylarktask.ui.adapters.RepoListAdapter
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(), RepoListAdapter.RepoListListener {

    //view parameters
    var userNameEt: EditText? = null
    var searchBtn: ImageButton? = null
    var userAvatarIv: CircleImageView? = null
    var fullNameTv: TextView? = null
    var companyTv: TextView? = null
    var bioTv: TextView? = null
    var userDetailLayout: ConstraintLayout? = null
    var recyclerView: RecyclerView? = null

    //viewModel object
    var viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding view parameters to views
        bindViews()

        //init view model instance
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        searchBtn?.setOnClickListener {

            if(TextUtils.isEmpty(userNameEt?.text.toString())){
                userNameEt?.error = "Please enter a valid username"
                return@setOnClickListener
            }

            viewModel.getUserDetails(userNameEt?.text.toString()).observe(this, {
                if(it != null){
                    //make user details layout visible if the api call is successful
                    userDetailLayout?.visibility = View.VISIBLE

                    setValues(it)

                    //if the api request is successful call get repository list
                    getRepositoryList()
                }
                else{
                    userDetailLayout?.visibility = View.GONE

                    Toast.makeText(this, "Make sure you have the correct username", Toast.LENGTH_LONG).show()
                }
            })

        }
    }

    private fun getRepositoryList() {
        viewModel.getRepositories(userNameEt?.text.toString()).observe(this, {
            if(it != null){
                val adapter = RepoListAdapter(this, it as ArrayList<GetRepositoryListResponseItem>, this)
                recyclerView?.adapter = adapter
            }
        })
    }

    private fun setValues(it: GetUserDetailsResponse) {
        fullNameTv?.text = it.name
        companyTv?.text = it.company
        bioTv?.text = it.bio

        try{
            Glide.with(this)
                .load(it.avatar_url)
                .into(userAvatarIv!!)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun bindViews() {
        userNameEt = findViewById(R.id.username_et)
        searchBtn = findViewById(R.id.search_btn)
        userDetailLayout =  findViewById(R.id.user_details_layout)
        userAvatarIv = findViewById(R.id.profile_pic_iv)
        fullNameTv = findViewById(R.id.name_tv)
        companyTv = findViewById(R.id.company_tv)
        bioTv = findViewById(R.id.bio_tv)

        //init recycler view to display list of repositories
        recyclerView = findViewById(R.id.repository_rv)
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    override fun onRepositoryClicked(item: GetRepositoryListResponseItem) {
        val i = Intent(this, ViewRepositoryDetailsActivity::class.java)
        i.putExtra("repo_details", item)
        startActivity(i)
    }


}