package com.example.breakingnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingnewsapp.R
import com.example.breakingnewsapp.data.network.dto.ArticleDto
import com.example.breakingnewsapp.databinding.ArticleItemBinding
import com.example.breakingnewsapp.domain.entity.Article
import com.example.breakingnewsapp.presentation.screens.HomeScreenFragmentDirections

class NewsListAdapter : ListAdapter<ArticleDto,
        NewsListAdapter.NewsListViewHolder>(DiffCallback) {

    inner class NewsListViewHolder(private val binding: ArticleItemBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(article: ArticleDto) {
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.author
            binding.tvTitle.text = article.title
            binding.tvSource.text = article.url
            Glide.with(itemView).load(article.urlToImage).into(binding.ivArticleImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsListAdapter.NewsListViewHolder {
        return NewsListViewHolder(ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false

        ))
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val article = getItem(position)

        holder.bind(article)
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(HomeScreenFragmentDirections
                    .actionHomeScreenFragmentToArticleScreenFragment(article))
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArticleDto> (){
        override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem.urlToImage == newItem.urlToImage
        }

        override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem == newItem
        }
    }
}