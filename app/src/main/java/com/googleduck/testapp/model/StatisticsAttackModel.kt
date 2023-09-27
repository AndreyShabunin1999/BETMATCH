package com.googleduck.testapp.model

import com.google.gson.annotations.SerializedName

data class StatisticsAttackModel (

    @SerializedName("Команда") var commands: String? = null,
    @SerializedName("Турнир") var tournament: String? = null,
    @SerializedName("Удары з.и.") var blows: Double? = null,
    @SerializedName("Удары ВСтв з.и.") var blowsVStv: Double? = null,
    @SerializedName("Дриблинг з.и.") var dribbling: Double? = null,
    @SerializedName("Дано фолов з.и.") var fouls: Double? = null,
    @SerializedName("Рейтинг") var rating: Double? = null

)
