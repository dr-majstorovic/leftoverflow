package com.example.leftoverflow.domain.repository

import com.example.leftoverflow.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun fetchAllRecipes(): Flow<List<Recipe>>

    fun fetchRecipeById(id: Long): Flow<Recipe?>

    suspend fun upsertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)
}