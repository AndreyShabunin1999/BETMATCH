package com.googleduck.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.model.TableModel

class TableRepository(private val apiInterface: ApiInterface) {
    private val tableLiveData = MutableLiveData<List<TableModel>>()

    val tables: LiveData<List<TableModel>>
        get() = tableLiveData

    suspend fun getTable() {
        val result = apiInterface.getTable()
        if (result.body() != null) {
            tableLiveData.postValue(result.body())
        }
    }
}
