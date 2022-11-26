package com.example.breakingnewsapp.domain.usecase

import com.example.breakingnewsapp.domain.repository.ArticleRepository

class LoadArticlesFromNetworkUseCase(private val repository: ArticleRepository) {

    suspend operator fun invoke(){
        repository.loadArticlesFromNetwork()
    }

}