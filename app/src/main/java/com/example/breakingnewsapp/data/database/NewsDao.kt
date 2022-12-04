package com.example.breakingnewsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.breakingnewsapp.data.models_db.ArticleCachingDao
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(articleFavoritesDao: ArticleFavoritesDao)

    @Delete
    suspend fun deleteArticle(articleFavoritesDao: ArticleFavoritesDao)

    @Query("SELECT * FROM articles ORDER BY id DESC")
    fun getArticlesFavorite(): LiveData<List<ArticleFavoritesDao>>

    @Query ("SELECT * FROM articles_caching")
    fun getArticlesCache(): List<ArticleCachingDao>

    @Query("DELETE FROM articles_caching")
    suspend fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCache(cahe: ArticleCachingDao)


}