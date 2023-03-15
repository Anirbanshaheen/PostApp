package com.example.postapp.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.databinding.ItemViewPostBinding
import com.example.postapp.models.PostModelItem

class PostAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val dataList: MutableList<PostModelItem> = mutableListOf()
    var onItemClick: ((model:PostModelItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemViewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewModel(binding)
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewModel) {
            val model = dataList[position]
            val binding = holder.binding
            binding.postTitleTV.text = model.title
            binding.postDescriptionTV.text = model.body
        }
    }

    inner class ViewModel(val binding: ItemViewPostBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(dataList[adapterPosition], adapterPosition)
                }
            }
        }
    }

    // For data load
    fun initLoad(list: List<PostModelItem>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}


