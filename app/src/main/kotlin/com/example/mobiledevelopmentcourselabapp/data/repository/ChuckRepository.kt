package com.example.mobiledevelopmentcourselabapp.data.repository

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.mapper.ChuckMapper
import javax.inject.Inject

class ChuckRepository @Inject constructor(
    private val api: ChuckApi,
    private val mapper: ChuckMapper
) {
    fun getRandomJoke() = api.getRandomJoke().map { mapper.mapResponse(it) }

    fun getJokeByCategory(category: String) = api.getJokeByCategory(category).map { mapper.mapResponse(it) }

    fun getCategories() = api.getCategories()
}