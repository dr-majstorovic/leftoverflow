package com.example.leftoverflow.domain.usecase

import com.example.leftoverflow.domain.repository.MealDetailRepository
import javax.inject.Inject

class MealDetailUseCase @Inject constructor(
    private val repository: MealDetailRepository
) {

    suspend operator fun invoke(id: String) =
        repository.fetchMealById(id)
}