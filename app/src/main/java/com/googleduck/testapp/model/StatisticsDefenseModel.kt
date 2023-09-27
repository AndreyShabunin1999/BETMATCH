package com.googleduck.testapp.model

import com.google.gson.annotations.SerializedName

data class StatisticsDefenseModel (

    @SerializedName("Команда") var comand :  String? = null,
    @SerializedName("Турнир") var tournament :  String? = null,
    @SerializedName("Удары з.и.") var blows : Double? = null,
    @SerializedName("Отборы з.и.") var  selections : Double? = null,
    @SerializedName("Перехваты з.и.") var interceptions : Double? = null,
    @SerializedName("Фолы з.и.") var fouls : Double? = null,
    @SerializedName("Офсайды з.и.") var offsides : Double? = null,
    @SerializedName("Рейтинг") var rating : Double? = null

)
