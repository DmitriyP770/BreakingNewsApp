package com.example.breakingnewsapp.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: Resource<ArticleResponceDto>){
    val adapter = recyclerView.adapter as  NewsListAdapter
    adapter.submitList(data.data?.articles)
}
