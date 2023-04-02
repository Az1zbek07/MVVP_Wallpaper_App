package com.example.wallpaperappmvvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperappmvvp.database.MyUrl
import com.example.wallpaperappmvvp.databinding.ItemLayoutBinding

class MyUrlAdapter: ListAdapter<MyUrl, MyUrlAdapter.VHolder>(DiffCallback()) {
    lateinit var onClick: (myUrl: MyUrl) -> Unit
    private class DiffCallback: DiffUtil.ItemCallback<MyUrl>(){
        override fun areItemsTheSame(oldItem: MyUrl, newItem: MyUrl): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyUrl, newItem: MyUrl): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(myUrl: MyUrl){
            with(binding){
                Glide.with(imageView).load(myUrl.url).into(imageView)
            }
        }
    }
}