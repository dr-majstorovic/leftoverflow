package com.example.leftoverflow.presentation.recipe

import com.example.leftoverflow.domain.model.Recipe

data class RecipeUiState(
    val recipes: List<Recipe> = emptyList()
)