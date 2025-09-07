package com.example.leftoverflow.data.repository

import com.example.leftoverflow.data.remote.LeftoverflowApiService
import com.example.leftoverflow.domain.mapper.asDomain
import com.example.leftoverflow.domain.model.FilterByCategory
import com.example.leftoverflow.domain.repository.FiltersRepository
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(
    private val api: LeftoverflowApiService
): FiltersRepository {

    override suspend fun fetchFilterByCategory(category: String): List<FilterByCategory> =
        api.getFilters(categories = category)
            .asDomain() ?: emptyList()
}