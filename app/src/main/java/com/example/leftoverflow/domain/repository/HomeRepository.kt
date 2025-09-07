package com.example.leftoverflow.domain.repository

import com.example.leftoverflow.domain.model.CategoriesMeal
import com.example.leftoverflow.domain.model.MinimalMeal
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchRandomMeal(): List<MinimalMeal>

    fun fetchCategories(): Flow<List<CategoriesMeal>>
}