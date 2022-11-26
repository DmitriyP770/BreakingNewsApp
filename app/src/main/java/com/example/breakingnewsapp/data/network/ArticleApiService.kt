package com.example.breakingnewsapp.data.network

import com.example.breakingnewsapp.BuildConfig
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.models_db.ArticleResponse
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
import com.example.breakingnewsapp.domain.repository.ArticleRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsapi.org/"
private const val API_KEY = BuildConfig.API_KEY


interface ArticleApiService {
    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,



    ): Response<ArticleResponceDto>


}

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(ArticleApiService::class.java)

        }
    }

}