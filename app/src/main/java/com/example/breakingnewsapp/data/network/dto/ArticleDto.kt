package com.example.breakingnewsapp.data.network.dto

import android.os.Parcelable
import com.example.breakingnewsapp.data.models_db.ArticleDao
import com.example.breakingnewsapp.data.models_db.SourceDao
import com.example.breakingnewsapp.domain.entity.Article
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto (
    val author: String?,
    val content: String?,
    @SerializedName("description")
    val description: String?,
    val publishedAt: String?,
    val sourceDao: SourceDao?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable {
    fun toArticleDao(): ArticleDao {
        return ArticleDao(
            author = author ?: "not found",
            content = content ?: "not found",
            description = description ?: "not found",
            publishedAt = publishedAt ?: "not found",
            sourceDao = sourceDao ?: SourceDao("", ""),
            title = title ?: "not found",
            url = url ?: "not found",
            urlToImage = urlToImage ?: "not found",
            id = 0
        )
    }
}
//
//    fun toArticle(): Article{
//        return Article(
//            author, content, description, title, url, urlToImage, sourceDao
//        )
//    }
//}
