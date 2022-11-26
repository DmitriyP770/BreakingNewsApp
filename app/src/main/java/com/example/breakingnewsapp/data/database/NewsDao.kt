package com.example.breakingnewsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.breakingnewsapp.data.models_db.ArticleDao
import kotlinx.coroutines.flow.Flow
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(articleDao: ArticleDao)

    @Delete
    suspend fun deleteArticle(articleDao: ArticleDao)

    @Query("SELECT * FROM articles ORDER BY id DESC")
    fun getArticles(): LiveData<List<ArticleDao>>
}