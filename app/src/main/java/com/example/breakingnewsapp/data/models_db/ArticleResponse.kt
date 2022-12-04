package com.example.breakingnewsapp.data.models_db

data class ArticleResponse(
    val articleFavoritesDaos: List<ArticleFavoritesDao>,
    val status: String,
    val totalResults: Int
)