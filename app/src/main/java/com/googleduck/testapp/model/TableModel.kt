package com.googleduck.testapp.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class TableModel (
    @SerializedName("name") var name: String? = null,
    @SerializedName("dates") var dates: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("players_count") var playersCount : Int? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("data") var data: JsonObject? = null
)
