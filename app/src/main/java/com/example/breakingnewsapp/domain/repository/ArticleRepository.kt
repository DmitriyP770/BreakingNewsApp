package com.example.breakingnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto

interface ArticleRepository {

    suspend fun saveArticle(articleFavoritesDao: ArticleFavoritesDao)

    suspend fun deleteArticle(articleFavoritesDao: ArticleFavoritesDao)

    fun getArticlesFromDb(): LiveData<List<ArticleFavoritesDao>>

    suspend fun loadArticlesFromNetwork(): Resource<ArticleResponceDto>

    suspend fun searchArticles(query: String): Resource<ArticleResponceDto>
}