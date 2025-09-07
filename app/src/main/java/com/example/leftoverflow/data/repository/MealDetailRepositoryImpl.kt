package com.example.leftoverflow.data.repository

import com.example.leftoverflow.data.remote.LeftoverflowApiService
import com.example.leftoverflow.domain.mapper.asDomain
import com.example.leftoverflow.domain.model.Meal
import com.example.leftoverflow.domain.repository.MealDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealDetailRepositoryImpl @Inject constructor(
    private val api: LeftoverflowApiService
): MealDetailRepository {

    override suspend fun fetchMealById(id: String): Meal =
        withContext(Dispatchers.IO) {
            api.getMealById(id).asDomain()
                .first()
        }
}