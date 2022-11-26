package com.example.breakingnewsapp.domain.usecase

import com.example.breakingnewsapp.domain.repository.ArticleRepository

class GetArticlesFromDbUseCase (private val repository: ArticleRepository){

    operator fun invoke(){
        repository.getArticlesFromDb()
    }
}