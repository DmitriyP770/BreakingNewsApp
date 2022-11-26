package com.example.breakingnewsapp.presentation.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.breakingnewsapp.data.database.ArticleDataBase
import com.example.breakingnewsapp.data.models_db.ArticleDao
import com.example.breakingnewsapp.data.network.dto.ArticleDto
import kotlinx.coroutines.launch

class SaveNewsViewModel(application: Application) : AndroidViewModel (application){
    val db = ArticleDataBase.getInstance(application)
   lateinit var articles: LiveData<List<ArticleDao>>

    fun saveNews(articleDto: ArticleDto){

        viewModelScope.launch {
            db.newsDao().saveArticle(articleDto.toArticleDao())
        }

    }

    fun getArticles(){
        viewModelScope.launch {
            articles = db.newsDao().getArticles()
        }
    }
    init {
        getArticles()
    }

}