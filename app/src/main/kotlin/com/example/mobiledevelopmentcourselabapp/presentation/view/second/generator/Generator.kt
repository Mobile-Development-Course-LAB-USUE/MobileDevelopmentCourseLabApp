package com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator


import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.AddStrClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.Position
import com.github.javafaker.Faker
import kotlin.random.Random

object   Generator {
    private const val PLAYERS_COUNT = 100
    var listURLS = arrayOf("https://logos-download.com/wp-content/uploads/2022/06/ChatGPT_Logo.png",
        "https://freebiehive.com/wp-content/uploads/2023/12/Google-Gemini-AI-Icon.jpg",
        "https://is5-ssl.mzstatic.com/image/thumb/Purple126/v4/9b/df/e9/9bdfe981-7da7-8a49-fe76-0159a4ba48f9/AppIcon-0-1x_U007emarketing-0-7-0-85-220.png/512x512bb.jpg",
        "https://neyrosety.ru/wp-content/uploads/2023/02/01132-3677842746-2559--1024x923.png",
        "https://cdn.betakit.com/wp-content/uploads/2023/08/Stability_AI_logo_Logo.jpg")
    var companyNames = arrayOf("OpenAI", "Google", "MidJourney", "Stability AI")
    var tasksNeuro = arrayOf("Мультимодальная нейросеть", "Генерация музыки", "Генерация изображений")
        fun generate(): List<ItemUIModel> {
        val faker = Faker()
        return mutableListOf<ItemUIModel>().apply {
            repeat(PLAYERS_COUNT) {
                var position = Position.values().random()
                add(
                    NeuroUIClass(
                        name = faker.funnyName().name(),
                        company = if (position.Name == "GPT-4") companyNames[0] else if (position.Name == "Gemini" || position.Name == "MusicFX") companyNames[1]
                        else if (position.Name == "MidJourney") companyNames[2] else companyNames[3],
                        number = if (position.Name == "GPT-4") (1..2).random() else if (position.Name == "Gemini") (1..2).random()
                        else if (position.Name == "MusicFX") 1  else if (position.Name == "MidJourney") (1..2).random() else (1..2).random(),
                        easy_content_velocity_gen = if (position.Name == "GPT-4" || position.Name == "Gemini") 5000
                        else if (position.Name == "MusicFX") 5 else if (position.Name == "MidJourney") 3  else 5,
                        hard_content_velocity_gen = if (position.Name == "GPT-4" || position.Name == "Gemini") 1500
                        else if (position.Name == "MusicFX") 1 else if (position.Name == "MidJourney") 2  else 2,
                        position = position,
                        neuroTask = if (position.Name == "GPT-4" || position.Name == "Gemini") tasksNeuro[0] else if (position.Name == "MusicFX")
                            tasksNeuro[1] else tasksNeuro[2],
                        photoUrl = if (position.Name == "GPT-4") listURLS[0] else if
                                (position.Name == "Gemini") listURLS[1] else if (position.Name == "MusicFX") listURLS[2] else if (
                                position.Name == "MidJourney") listURLS[3] else listURLS[4]
                    )
                )
                add (
                    AddStrClass(
                        description = "Ilon Mask",
                        photo = "https://u2.9111s.ru/uploads/202301/06/e296190b84f13fd4e2f9a7f36bc3b311.jpg"
                    )
                )
                if (Random.nextBoolean()) add(AdUiModel(faker.funnyName().name()))
            }
        }
    }

}