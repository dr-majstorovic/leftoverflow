package com.example.leftoverflow.domain.repository

import androidx.paging.PagingData
import com.example.leftoverflow.domain.model.MinimalMeal
import kotlinx.coroutines.flow.Flow

interface SearchMealRepository {

    fun fetchSearchedMeals(
        query: String,
        //queryByLetter: String?
    ): Flow<PagingData<MinimalMeal>>
}