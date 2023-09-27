package com.googleduck.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.googleduck.testapp.repository.StatisticsDefenseRepository

class StatisticsDefenseViewModelFactory(private val statisticsRepository: StatisticsDefenseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatisticsDefenseViewModel(statisticsRepository) as T
    }
}