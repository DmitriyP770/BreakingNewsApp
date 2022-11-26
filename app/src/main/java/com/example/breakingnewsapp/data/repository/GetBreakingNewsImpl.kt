package com.example.breakingnewsapp.data.repository

import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.network.RetrofitInstance
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
import retrofit2.Response

class GetBreakingNewsImpl() {
     suspend fun getNews(): Resource<ArticleResponceDto> {
        return try {
            val response = RetrofitInstance.api.getArticles()
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                return Resource.Error(response.message())
            }

        } catch (e: Exception){
            return Resource.Error(e.message?: "An error occurred")
        }
    }

}