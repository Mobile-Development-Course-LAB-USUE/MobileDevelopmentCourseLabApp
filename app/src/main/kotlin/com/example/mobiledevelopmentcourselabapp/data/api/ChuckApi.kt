package com.example.mobiledevelopmentcourselabapp.data.api

import com.example.mobiledevelopmentcourselabapp.data.model.ChuckJokeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckApi {
    @GET("jokes/random")
    fun getRandomJoke(): Single<ChuckJokeResponse>

    @GET("jokes/random")
    fun getJokeByCategory(@Query("category") category: String): Single<ChuckJokeResponse>

    @GET("jokes/categories")
    fun getCategories(): Single<List<String>>
}