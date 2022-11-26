package com.example.breakingnewsapp.domain.repository

import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.models_db.ArticleDao
import com.example.breakingnewsapp.data.models_db.ArticleResponse
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ArticleRepository {

    suspend fun saveArticle(articleDao: ArticleDao)

    suspend fun deleteArticle(articleDao: ArticleDao)

    fun getArticlesFromDb(): Flow<List<ArticleDao>>

    suspend fun loadArticlesFromNetwork(): Flow<Resource<ArticleResponceDto>>
}