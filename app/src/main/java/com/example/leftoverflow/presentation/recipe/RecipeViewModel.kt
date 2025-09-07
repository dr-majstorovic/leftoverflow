package com.example.leftoverflow.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leftoverflow.domain.model.Recipe
import com.example.leftoverflow.domain.usecase.DeleteRecipesUseCase
import com.example.leftoverflow.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    getRecipesUseCase: GetRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipesUseCase
): ViewModel() {

    val recipes: StateFlow<RecipeUiState> =
        getRecipesUseCase.invoke()
            .map { RecipeUiState(recipes = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RecipeUiState()
            )

    fun deleteRecipe(recipe: Recipe) =
        viewModelScope.launch {
            deleteRecipeUseCase.invoke(recipe)
        }
}