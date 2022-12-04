package com.example.breakingnewsapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.database.ArticleDataBase
import com.example.breakingnewsapp.data.models_db.ArticleCachingDao
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.data.network.RetrofitInstance
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
import com.example.breakingnewsapp.data.network.dto.toArticleCache
import com.example.breakingnewsapp.domain.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArticleRepositoryImpl(val application: Application) : ArticleRepository {

    override suspend fun saveArticle(articleFavoritesDao: ArticleFavoritesDao) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(articleFavoritesDao: ArticleFavoritesDao) {
        TODO("Not yet implemented")
    }

    override fun getArticlesFromDb(): LiveData<List<ArticleFavoritesDao>> {
        return ArticleDataBase.getInstance(application).newsDao().getArticlesFavorite()
    }

    override suspend fun searchArticles(query: String): Resource<ArticleResponceDto> {
        return try {
            val response = RetrofitInstance.api.searchArticles(query)
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else return Resource.Error(response.message())
        } catch (e:Exception){
            return Resource.Error(e.message?: "An error occurred")
        }
    }

    override suspend fun loadArticlesFromNetwork(): Resource<ArticleResponceDto> {
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

    suspend fun getCachingArticles(): Flow<Resource<List<ArticleCachingDao>>>{
        return flow {
            val dao = ArticleDataBase.getInstance(application).newsDao()
            emit(Resource.Loading())
            val cache = dao.getArticlesCache()
            emit(Resource.Cache(data = cache))
            try{
                val response = RetrofitInstance.api.getArticles()
                val result = response.body()
                if (response.isSuccessful && result != null ){
                   dao.clearTable()
                   result.articles.forEach {
                       dao.saveCache(it.toArticleCache())
                   }
                    emit(Resource.Success(dao.getArticlesCache()))
                } else{
                    emit(Resource.Error("something went wrong"))
                }
            }  catch (e: Exception){
                emit(Resource.Error(e.message?: "An error occurred"))
            }
        }.flowOn(Dispatchers.IO)
    }

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