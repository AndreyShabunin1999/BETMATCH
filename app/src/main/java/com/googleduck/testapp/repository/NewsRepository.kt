package com.googleduck.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.model.NewsModel

class NewsRepository(private val apiInterface: ApiInterface) {
    private val newsLiveData = MutableLiveData<List<NewsModel>>()

    val news: LiveData<List<NewsModel>>
        get() = newsLiveData

    suspend fun getNews() {
        val result = apiInterface.getNews()
        if(result.body() != null) {
            newsLiveData.postValue(result.body())
        }
    }
}