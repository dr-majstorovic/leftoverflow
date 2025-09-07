package com.example.leftoverflow.presentation.recipe.new_recipe

import com.example.leftoverflow.domain.model.Recipe

sealed interface NewRecipeUiEvents {
    data class AddToDataBase(val add: Recipe): NewRecipeUiEvents
}