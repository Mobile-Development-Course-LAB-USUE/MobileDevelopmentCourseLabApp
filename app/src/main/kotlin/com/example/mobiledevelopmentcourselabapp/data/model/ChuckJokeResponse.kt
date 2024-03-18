package com.example.mobiledevelopmentcourselabapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChuckJokeResponse(
    @SerializedName("icon_url") val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)