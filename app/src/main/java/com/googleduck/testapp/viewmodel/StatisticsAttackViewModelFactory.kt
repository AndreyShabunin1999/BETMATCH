package com.googleduck.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.googleduck.testapp.repository.StatisticsAttackRepository

class StatisticsAttackViewModelFactory(private val statisticsAttackRepository: StatisticsAttackRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatisticsAttackViewModel(statisticsAttackRepository) as T
    }
}
