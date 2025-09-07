package com.example.leftoverflow.presentation.recipe.detail

import com.example.leftoverflow.domain.model.Recipe

sealed interface RecipeDetailUiEvents {
    data object NavigateBack: RecipeDetailUiEvents
    data class DeleteRecipe(val remove: Recipe): RecipeDetailUiEvents
}