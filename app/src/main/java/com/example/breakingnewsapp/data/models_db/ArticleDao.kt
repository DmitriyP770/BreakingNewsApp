package com.example.breakingnewsapp.data.models_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingnewsapp.domain.entity.Article

@Entity(tableName = "articles")
data class ArticleDao(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceDao: SourceDao,
    val title: String,
    val url: String,
    val urlToImage: String
){
    fun toArticle(): Article{
        return Article(author, content, description, title, url, urlToImage, sourceDao)
    }
}