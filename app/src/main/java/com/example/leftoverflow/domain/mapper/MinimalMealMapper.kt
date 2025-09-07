package com.example.leftoverflow.domain.mapper

import com.example.leftoverflow.data.local.entity.MinimalMealEntity
import com.example.leftoverflow.data.remote.dto.MealMinimalListDto
import com.example.leftoverflow.domain.model.MinimalMeal

fun MealMinimalListDto.asEntity() =
    this.meals.map { data ->
        MinimalMealEntity(
            idMeal = data.idMeal.orEmpty(),
            strMeal = data.strMeal.orEmpty(),
            strMealThumb = data.strMealThumb.orEmpty(),
            strCategory = data.strCategory.orEmpty()
        )
    }

fun MealMinimalListDto.asDomain() =
    this.meals.map { data ->
        MinimalMeal(
            idMeal = data.idMeal.orEmpty(),
            strMeal = data.strMeal.orEmpty(),
            strMealThumb = data.strMealThumb.orEmpty(),
            strCategory = data.strCategory.orEmpty()
        )
    }

fun MinimalMealEntity.asDomain() =
    MinimalMeal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        strCategory = strCategory
    )