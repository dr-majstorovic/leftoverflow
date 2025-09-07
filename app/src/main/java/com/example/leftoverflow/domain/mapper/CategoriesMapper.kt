package com.example.leftoverflow.domain.mapper

import com.example.leftoverflow.data.local.entity.CategoriesEntity
import com.example.leftoverflow.data.remote.dto.CategoriesDto
import com.example.leftoverflow.domain.model.CategoriesMeal

fun CategoriesDto.asEntity() =
    this.categories?.map { data ->
        CategoriesEntity(
            idMeal = data?.idCategory.orEmpty(),
            strName = data?.strCategory.orEmpty(),
            strThumb = data?.strCategoryThumb.orEmpty()
        )
    }

fun CategoriesEntity.asDomain() =
    CategoriesMeal(
        idMeal = idMeal,
        strName = strName,
        strThumb = strThumb
    )