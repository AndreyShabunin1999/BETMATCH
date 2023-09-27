package com.googleduck.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.model.StatisticsAttackModel

class StatisticsAttackRepository(private val apiInterface: ApiInterface) {
    private val statisticsAttackLiveData = MutableLiveData<List<StatisticsAttackModel>>()

    val stat: LiveData<List<StatisticsAttackModel>>
        get() = statisticsAttackLiveData

    suspend fun getAttackStatistics() {
        val result = apiInterface.getAttackStatistics()
        if(result.body() != null) {
            statisticsAttackLiveData.postValue(result.body())
        }
    }
}