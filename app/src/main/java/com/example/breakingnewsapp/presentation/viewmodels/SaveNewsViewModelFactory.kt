package com.example.breakingnewsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SaveNewsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveNewsViewModel::class.java)) {
            return SaveNewsViewModel(application) as T
        }
        throw RuntimeException()
    }
}