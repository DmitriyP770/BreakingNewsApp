package com.example.breakingnewsapp.domain.entity

import com.example.breakingnewsapp.data.models_db.SourceDao

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val sourceDao: SourceDao,

    )
