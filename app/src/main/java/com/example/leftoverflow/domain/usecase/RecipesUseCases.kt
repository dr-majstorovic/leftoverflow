package com.example.leftoverflow.domain.usecase

import com.example.leftoverflow.domain.model.Recipe
import com.example.leftoverflow.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke() =
        repository.fetchAllRecipes()
}

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Long) =
        repository.fetchRecipeById(id)
}

class UpsertRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) =
        repository.upsertRecipe(recipe)
}

class DeleteRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) =
        repository.deleteRecipe(recipe)
}