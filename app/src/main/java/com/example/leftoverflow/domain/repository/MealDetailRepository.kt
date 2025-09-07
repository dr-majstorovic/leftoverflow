package com.example.leftoverflow.domain.repository

import com.example.leftoverflow.domain.model.Meal

interface MealDetailRepository {

    suspend fun fetchMealById(id: String): Meal
}