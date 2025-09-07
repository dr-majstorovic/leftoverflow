package com.example.leftoverflow.domain.usecase

import com.example.leftoverflow.domain.repository.SearchMealRepository
import javax.inject.Inject

class SearchMealUseCase @Inject constructor(
    private val repository: SearchMealRepository
) {
    operator fun invoke(query: String) =
        repository.fetchSearchedMeals(query = query)
}