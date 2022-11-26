package com.example.breakingnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingnewsapp.data.models_db.ArticleDao
import com.example.breakingnewsapp.databinding.ArticleItemBinding
import com.example.breakingnewsapp.databinding.FragmentFavoriteNewsScreenBinding

class SaveNewsAdapter : ListAdapter<ArticleDao,SaveNewsAdapter.SaveNewsViewHolder>(DiffCallBack) {

    inner class SaveNewsViewHolder(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(articleDao: ArticleDao){
                with(binding){
                    Glide.with(itemView).load(articleDao.urlToImage).into(ivArticleImage)
                    tvDescription.text = articleDao.description
                    tvPublishedAt.text = articleDao.publishedAt
                    tvTitle.text = articleDao.title
                    tvSource.text = articleDao.url
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

    companion object DiffCallBack : DiffUtil.ItemCallback<ArticleDao>(){

        override fun areItemsTheSame(oldItem: ArticleDao, newItem: ArticleDao): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleDao, newItem: ArticleDao): Boolean {
            return oldItem == newItem
        }
    }
}