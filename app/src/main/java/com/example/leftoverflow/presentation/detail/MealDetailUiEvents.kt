package com.example.leftoverflow.presentation.detail

import com.example.leftoverflow.domain.model.Meal

sealed interface MealDetailUiEvents {
    data object NavigateBack : MealDetailUiEvents
    data class AddToBookmark(val add: Meal): MealDetailUiEvents
    data class RemoveToBookmark(val remove: Meal) : MealDetailUiEvents
}