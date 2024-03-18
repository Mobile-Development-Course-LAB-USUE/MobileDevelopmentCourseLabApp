package com.example.mobiledevelopmentcourselabapp.data.mapper

import com.example.mobiledevelopmentcourselabapp.data.model.ChuckJokeResponse
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import javax.inject.Inject

class ChuckMapper @Inject constructor() {
    fun mapResponse(response: ChuckJokeResponse) =
        ChuckJokeEntity(
            iconUrl = response.iconUrl,
            value = response.value
        )
}