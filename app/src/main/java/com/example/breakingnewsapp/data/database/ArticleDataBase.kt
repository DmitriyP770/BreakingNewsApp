package com.example.breakingnewsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingnewsapp.data.models_db.ArticleCachingDao
import com.example.breakingnewsapp.data.models_db.ArticleFavoritesDao
import com.example.breakingnewsapp.data.util.GsonParser
import com.google.gson.Gson

@Database(entities = [ArticleFavoritesDao::class, ArticleCachingDao::class], version = 2)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: ArticleDataBase? = null
        val LOCK = Any()

        fun getInstance(context: Context): ArticleDataBase{
            return INSTANCE ?: synchronized(LOCK){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDataBase::class.java,
                    "articles_db"
                )
                    .addTypeConverter(Converters(GsonParser(Gson())))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}