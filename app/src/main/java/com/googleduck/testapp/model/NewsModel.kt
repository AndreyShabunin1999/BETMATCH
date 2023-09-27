package com.googleduck.testapp.model

import com.google.gson.annotations.SerializedName

data class NewsModel (

    @SerializedName("tittle") var tittle : String? = null,
    @SerializedName("text") var text   : String? = null,
    @SerializedName("img") var img    : String? = null,
    @SerializedName("date") var date   : String? = null

)
