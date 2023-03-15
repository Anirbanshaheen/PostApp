package com.example.postapp.screens.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.databinding.ItemViewPostCommentBinding
import com.example.postapp.models.postCommentModel.PostCommentModelItem

class PostCommentAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val dataList: MutableList<PostCommentModelItem> = mutableListOf()
    var onItemClick: ((model: PostCommentModelItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemViewPostCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewModel(binding)
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewModel) {
            val model = dataList[position]
            val binding = holder.binding
            binding.postCommentTitleTV.text = model.name
            binding.postCommentDescriptionTV.text = model.body
        }
    }

    inner class ViewModel(val binding: ItemViewPostCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(dataList[adapterPosition], adapterPosition)
                }
            }
        }
    }

    // For data load
    fun initLoad(list: List<PostCommentModelItem>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}


