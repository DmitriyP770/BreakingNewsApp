package com.example.breakingnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.databinding.ArticleItemBinding

class SaveNewsAdapter : ListAdapter<ArticleFavoritesDao,SaveNewsAdapter.SaveNewsViewHolder>(DiffCallBack) {

    inner class SaveNewsViewHolder(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(articleFavoritesDao: ArticleFavoritesDao){
                with(binding){
                    Glide.with(itemView).load(articleFavoritesDao.urlToImage).into(ivArticleImage)
                    tvDescription.text = articleFavoritesDao.description
                    tvPublishedAt.text = articleFavoritesDao.publishedAt
                    tvTitle.text = articleFavoritesDao.title
                    tvSource.text = articleFavoritesDao.url
                    executePendingBindings()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveNewsViewHolder {
        return SaveNewsViewHolder(
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SaveNewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ArticleFavoritesDao>(){

        override fun areItemsTheSame(oldItem: ArticleFavoritesDao, newItem: ArticleFavoritesDao): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleFavoritesDao, newItem: ArticleFavoritesDao): Boolean {
            return oldItem == newItem
        }
    }
}