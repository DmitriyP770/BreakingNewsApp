package com.example.breakingnewsapp.domain.usecase

import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.domain.repository.ArticleRepository

class SaveArticleUseCase (private val repository: ArticleRepository) {

    suspend operator fun invoke(articleFavoritesDao: ArticleFavoritesDao){
        repository.saveArticle(articleFavoritesDao)
    }

}