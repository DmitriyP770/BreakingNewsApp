package com.example.breakingnewsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.breakingnewsapp.data.database.ArticleDataBase
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.data.network.dto.ArticleDto
import kotlinx.coroutines.launch

class SaveNewsViewModel(application: Application) : AndroidViewModel (application){
    val db = ArticleDataBase.getInstance(application)
   lateinit var articles: LiveData<List<ArticleFavoritesDao>>

    fun saveNews(articleFavoritesDao: ArticleFavoritesDao){

        viewModelScope.launch {
            db.newsDao().saveArticle(articleFavoritesDao)
        }

    }

    fun getArticles(){
        viewModelScope.launch {
            articles = db.newsDao().getArticlesFavorite()
        }
    }
    init {
        getArticles()
    }

}