package com.googleduck.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleduck.testapp.model.NewsModel
import com.googleduck.testapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            newsRepository.getNews()
        }
    }

    val news: LiveData<List<NewsModel>>
        get() = newsRepository.news
}
