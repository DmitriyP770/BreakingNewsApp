package com.example.breakingnewsapp.data.models_db

data class ArticleResponse(
    val articleDaos: List<ArticleDao>,
    val status: String,
    val totalResults: Int
)