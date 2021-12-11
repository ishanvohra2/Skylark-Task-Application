package com.ishanvohra.skylarktask.ui.activities.viewRepoDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ishanvohra.skylarktask.R
import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import de.hdodenhof.circleimageview.CircleImageView

class ViewRepositoryDetailsActivity : AppCompatActivity() {

    //view parameters
    var backBtn: ImageButton? = null
    var repoIv: CircleImageView? = null
    var userNameTv: TextView? = null
    var repoNameTv: TextView? = null
    var repoDescription: TextView? = null
    var starTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_repository_details)

        //getting repo details via intent
        val repo = intent.getSerializableExtra("repo_details") as GetRepositoryListResponseItem

        //bind view parameters to view elements
        bindViews()

        //if repo is not null then set values
        if(repo != null)
            setValues(repo)

        //close activity when backbutton is clicked
        backBtn?.setOnClickListener {
            finish()
        }
    }

    private fun setValues(repo: GetRepositoryListResponseItem) {
        userNameTv?.text = repo.owner.login
        repoNameTv?.text = repo.name
        if(repo.description != null){
            repoDescription?.text = repo.description
        }

        if(repo.stargazers_count != null)
            starTv?.text = "${repo.stargazers_count} stars"

        try{
            Glide.with(this)
                .load(repo.owner.avatar_url)
                .into(repoIv!!)
        }
        catch (e: Exception){
            e.printStackTrace()
        }

    }

    private fun bindViews() {
        backBtn = findViewById(R.id.back_btn)
        repoIv = findViewById(R.id.repo_iv)
        userNameTv = findViewById(R.id.username_tv)
        repoNameTv = findViewById(R.id.repo_name_tv)
        repoDescription = findViewById(R.id.repo_description_tv)
        starTv = findViewById(R.id.star_tv)
    }
}