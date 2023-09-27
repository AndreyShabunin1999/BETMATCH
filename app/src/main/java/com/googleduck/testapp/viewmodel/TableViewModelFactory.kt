package com.googleduck.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.googleduck.testapp.repository.StatisticsDefenseRepository
import com.googleduck.testapp.repository.TableRepository

class TableViewModelFactory(private val tableRepository: TableRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TableViewModel(tableRepository) as T
    }
}