package com.ishanvohra.skylarktask.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishanvohra.skylarktask.R
import com.ishanvohra.skylarktask.models.GetRepositoryListResponseItem
import de.hdodenhof.circleimageview.CircleImageView

/*
Adapter class for recycler view to display list of repositories
 */

class RepoListAdapter (
    val context: Context,
    val dataSet: ArrayList<GetRepositoryListResponseItem>,
    val listener: RepoListListener
    ): RecyclerView.Adapter<RepoListAdapter.MyViewHolder>() {

       interface RepoListListener{
           fun onRepositoryClicked(item: GetRepositoryListResponseItem)
       }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val repoImageView: CircleImageView = itemView.findViewById(R.id.repo_iv)
        val repoNameTv: TextView = itemView.findViewById(R.id.repo_name_tv)
        val userNameTv: TextView = itemView.findViewById(R.id.username_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataSet[position]

        holder.repoNameTv.text = item.name
        holder.userNameTv.text = item.owner.login

        try{
            Glide.with(context)
                .load(item.owner.avatar_url)
                .into(holder.repoImageView)
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        holder.itemView.setOnClickListener {
            listener.onRepositoryClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}