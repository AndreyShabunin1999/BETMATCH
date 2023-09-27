package com.googleduck.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleduck.testapp.model.TableModel
import com.googleduck.testapp.repository.TableRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TableViewModel(private val tableRepository: TableRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            tableRepository.getTable()
        }
    }

    val tables: LiveData<List<TableModel>>
        get() = tableRepository.tables
}
