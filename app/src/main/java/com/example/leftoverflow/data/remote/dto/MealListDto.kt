package com.example.leftoverflow.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealListDto(
    val meals: List<MealDto> = listOf()
)

@Serializable
data class MealMinimalListDto(
    val meals: List<MinimalMealDto> = listOf()
)