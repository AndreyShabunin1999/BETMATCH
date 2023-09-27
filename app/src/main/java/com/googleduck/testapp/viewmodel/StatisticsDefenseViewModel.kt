package com.googleduck.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleduck.testapp.model.StatisticsDefenseModel
import com.googleduck.testapp.repository.StatisticsDefenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsDefenseViewModel(private val statisticsRepository: StatisticsDefenseRepository): ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO){
            statisticsRepository.getDefenseStatistics()
        }
    }

    val stat: LiveData<List<StatisticsDefenseModel>>
    get() = statisticsRepository.stat
}