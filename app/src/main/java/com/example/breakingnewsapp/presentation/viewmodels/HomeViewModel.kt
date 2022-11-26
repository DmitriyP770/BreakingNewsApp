package com.example.breakingnewsapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.data.network.RetrofitInstance
import com.example.breakingnewsapp.data.network.dto.ArticleDto
import com.example.breakingnewsapp.data.network.dto.ArticleResponceDto
import com.example.breakingnewsapp.data.repository.GetBreakingNewsImpl

import com.example.breakingnewsapp.domain.entity.Article
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

class HomeViewModel() : ViewModel() {


    private val repo = GetBreakingNewsImpl()
    private val _state =MutableLiveData<Resource<ArticleResponceDto>>()
    val newsList:LiveData<Resource<ArticleResponceDto>>
        get() = _state

    val respone = MutableLiveData<Resource<ArticleResponceDto>>()
    private val newsDtoList = mutableListOf<ArticleDto>()
    private val actList = mutableListOf<Article>()

    private val _modelState = MutableStateFlow<ModelEvents>(ModelEvents.Empty)
    val modelState: StateFlow<ModelEvents>
        get() = _modelState

    fun getNews(){
        viewModelScope.launch {
            _modelState.value = ModelEvents.Loading
            val request = repo.getNews()
            when(request){
                is Resource.Error -> {
                    _modelState.value = ModelEvents.Failure(request.message?: "Some error while api calling")
                }
                is Resource.Loading ->{
                    _modelState.value = ModelEvents.Loading
                }

                is Resource.Success ->{
                    _modelState.value = ModelEvents.Success(request.data!!.articles)
                }
            }
        }
    }

//    private suspend fun getArticles() = repository.loadArticlesFromNetwork()

//    private fun getArticles(){
//        viewModelScope.launch {
//        }
//    }\

    sealed class ModelEvents{
        class Success(val result: List<ArticleDto>): ModelEvents()
        class Failure(val message: String): ModelEvents()
        object Loading : ModelEvents()
        object Empty : ModelEvents()
    }


    private fun handleResponse(response: Response<ArticleResponceDto>):Resource<ArticleResponceDto>
    {
        if (response.isSuccessful){
           return Resource.Success(data = response.body())
        }
        return Resource.Error("Something went wrong with request")
    }

//
//    fun setNewsList(responceDto: ArticleResponceDto){
//        val list = mutableListOf<Article>()
//        articleResponceDto.articleDaos.forEach {
//           list.add(it.toArticle())
//
//        }
//        _newsList.value = list
//    }

    init {
        getNews()
    }

}