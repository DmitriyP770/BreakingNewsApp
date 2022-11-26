//package com.example.breakingnewsapp.data.repository
//
//import android.content.Context
//import com.example.breakingnewsapp.core.util.Resource
//import com.example.breakingnewsapp.data.database.ArticleDataBase
//import com.example.breakingnewsapp.data.models_db.ArticleDao
//import com.example.breakingnewsapp.data.network.ArticleApiService
//import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
//import com.example.breakingnewsapp.domain.repository.ArticleRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import java.io.IOException
//
//class ArticleRepositoryImpl(
//    private val context: Context
//    ) : ArticleRepository {
//    private val db:ArticleDataBase = ArticleDataBase.getInstance(context)
//    private val apiService: ArticleApiService =ArticleApiService.provideApi()
//
//    override suspend fun saveArticle(articleDao: ArticleDao) {
//        db.newsDao().saveArticle(articleDao)
//    }
//
//    override suspend fun deleteArticle(articleDao: ArticleDao) {
//        db.newsDao().deleteArticle(articleDao)
//    }
//
//    override fun getArticlesFromDb(): Flow<List<ArticleDao>> {
//      return db.newsDao().getArticles()
//    }
//
//    override suspend fun loadArticlesFromNetwork(): Flow<Resource<ArticleResponceDto>> = flow {
//        emit(Resource.Loading())
//        try {
//            val remoteArticles = apiService.getArticles()
//            emit(Resource.Success(remoteArticles))}
//        catch (e:HttpException){
//            emit(Resource.Error("something went wrong", null))
//        }
//            catch (e: IOException){
//                emit(Resource.Error("something went wrong", null))
//    }
//
//
//        }
//    }
