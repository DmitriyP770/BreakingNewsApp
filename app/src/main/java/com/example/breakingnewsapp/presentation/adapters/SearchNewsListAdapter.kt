package com.example.breakingnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingnewsapp.data.network.dto.ArticleDto
import com.example.breakingnewsapp.databinding.ArticleItemBinding

class SearchNewsListAdapter :
    ListAdapter<ArticleDto, SearchNewsListAdapter.SearchNewsViewHolder>(DiffCallback) {

    inner class SearchNewsViewHolder(private val binding : ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(articleDto : ArticleDto) {
                with(binding){
                    tvSource.text = articleDto.sourceDao?.name ?: ""
                    tvTitle.text = articleDto.title
                    tvDescription.text = articleDto.description
                    tvPublishedAt.text = articleDto.publishedAt

                }
                Glide.with(itemView).load(articleDto.urlToImage).into(binding.ivArticleImage)
            }
        }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : SearchNewsViewHolder {
        return SearchNewsViewHolder(ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder : SearchNewsViewHolder, position : Int) {
        val article = getItem(position)
        holder.bind(article)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArticleDto>() {
        override fun areItemsTheSame(oldItem : ArticleDto, newItem : ArticleDto) : Boolean {
           return oldItem.urlToImage == newItem.urlToImage
        }

        override fun areContentsTheSame(oldItem : ArticleDto, newItem : ArticleDto) : Boolean {
            return oldItem == newItem
        }
    }
}