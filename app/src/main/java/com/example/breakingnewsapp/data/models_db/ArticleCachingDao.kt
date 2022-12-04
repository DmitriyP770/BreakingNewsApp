package com.example.breakingnewsapp.data.models_db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles_caching")
data class ArticleCachingDao (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceDao: SourceDao,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable

fun ArticleCachingDao.toFavorites(): ArticleFavoritesDao{
    return ArticleFavoritesDao(
        id, author, content, description, publishedAt, sourceDao, title, url, urlToImage
    )
}