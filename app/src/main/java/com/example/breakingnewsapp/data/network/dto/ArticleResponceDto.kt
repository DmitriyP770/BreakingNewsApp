package com.example.breakingnewsapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ArticleResponceDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    val status: String?,
    val totalResults: Int
)
//{
//    fun toArtclesEntity(): Articles{
//        return Articles(articleList = articleDaos.map {
//            it.toArticle()
//        })
//    }
//}
