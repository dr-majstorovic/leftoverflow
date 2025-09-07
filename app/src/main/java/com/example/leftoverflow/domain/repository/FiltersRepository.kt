package com.example.leftoverflow.domain.repository

import com.example.leftoverflow.domain.model.FilterByCategory

interface FiltersRepository {

    suspend fun fetchFilterByCategory(category: String): List<FilterByCategory>
}