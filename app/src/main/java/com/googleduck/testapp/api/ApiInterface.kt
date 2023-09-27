package com.googleduck.testapp.api

import com.googleduck.testapp.model.NewsModel
import com.googleduck.testapp.model.StatisticsAttackModel
import com.googleduck.testapp.model.StatisticsDefenseModel
import com.googleduck.testapp.model.TableModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/data_statistic_defense.json")
    suspend fun getDefenseStatistics() : Response<List<StatisticsDefenseModel>>

    @GET("api/data_statistic_attack.json")
    suspend fun getAttackStatistics() : Response<List<StatisticsAttackModel>>

    @GET("api/news.json")
    suspend fun getNews() : Response<List<NewsModel>>

    @GET("api/data_tournament_tables.json")
    suspend fun getTable() : Response<List<TableModel>>
}