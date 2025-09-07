package com.example.leftoverflow.domain.mapper

import com.example.leftoverflow.data.remote.dto.FilterDto
import com.example.leftoverflow.domain.model.FilterByCategory

fun FilterDto.asDomain() =
    this.meals?.map { data ->
        FilterByCategory(
            idMeal = data?.idMeal.orEmpty(),
            strMeal = data?.strMeal.orEmpty(),
            strMealThumb = data?.strMealThumb.orEmpty()
        )
    }