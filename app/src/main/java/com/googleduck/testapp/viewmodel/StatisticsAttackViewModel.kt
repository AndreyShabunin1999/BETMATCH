package com.googleduck.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleduck.testapp.model.StatisticsAttackModel
import com.googleduck.testapp.repository.StatisticsAttackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StatisticsAttackViewModel(private val statisticsAttackRepository: StatisticsAttackRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            statisticsAttackRepository.getAttackStatistics()
        }
    }

    val stat: LiveData<List<StatisticsAttackModel>>
        get() = statisticsAttackRepository.stat
}

