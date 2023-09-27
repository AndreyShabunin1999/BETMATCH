package com.googleduck.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.model.StatisticsDefenseModel

class StatisticsDefenseRepository(private val apiInterface: ApiInterface) {
    private val statisticsLiveData = MutableLiveData<List<StatisticsDefenseModel>>()

    val stat: LiveData<List<StatisticsDefenseModel>>
    get() = statisticsLiveData

    suspend fun getDefenseStatistics() {
        val result = apiInterface.getDefenseStatistics()
        if(result.body() != null) {
            statisticsLiveData.postValue(result.body())
        }
    }
}