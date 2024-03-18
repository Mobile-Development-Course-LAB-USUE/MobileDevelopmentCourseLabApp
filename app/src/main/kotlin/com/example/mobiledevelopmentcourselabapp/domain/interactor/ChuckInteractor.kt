package com.example.mobiledevelopmentcourselabapp.domain.interactor

import com.example.mobiledevelopmentcourselabapp.data.repository.ChuckRepository
import javax.inject.Inject

class ChuckInteractor @Inject constructor(
    private val repository: ChuckRepository
) {
    fun getRandomJoke() = repository.getRandomJoke()

    fun getJokeByCategory(category: String) = repository.getJokeByCategory(category)

    fun getCategories() = repository.getCategories()
}