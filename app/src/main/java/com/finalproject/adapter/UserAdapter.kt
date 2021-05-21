package com.finalproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.data.api.response.UserItem
import com.finalproject.databinding.ItemGridUserBinding
import com.finalproject.utils.setImageUrl

class UserAdapter(
    val context: Context,
    val data: MutableList<UserItem> = mutableListOf()
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemGridUserBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemGridUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: UserItem){
            binding.ivUser.setImageUrl(context,data.avatar_url, binding.pbUser)
            binding.tvId.text = data.id.toString()
            binding.tvName.text = data.login
        }
    }
}