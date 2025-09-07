package com.example.leftoverflow.data.remote

import com.example.leftoverflow.data.remote.dto.CategoriesDto
import com.example.leftoverflow.data.remote.dto.FilterDto
import com.example.leftoverflow.data.remote.dto.MealListDto
import com.example.leftoverflow.data.remote.dto.MealMinimalListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LeftoverflowApiService {

    @GET(RANDOM_MEAL)
    suspend fun getRandomMeal(): MealMinimalListDto

    @GET(CATEGORIES)
    suspend fun getCategories(): CategoriesDto

    @GET(SEARCH_MEALS)
    suspend fun getSearchMeals(
        @Query("page") page: String? = null,
        @Query("s") query: String? = null,
        //@Query("f") firstLetter: String? = null
    ): MealMinimalListDto

    @GET(MEAL_BY_ID)
    suspend fun getMealById(@Query("i") id: String): MealListDto

    @GET(GENERAL_LIST)
    suspend fun getList(
        @Query("c") categories: String? = null,
        @Query("a") areas: String? = null,
        @Query("i") ingredients: String? = null
    ): Any

    @GET(GENERAL_FILTER)
    suspend fun getFilters(
        @Query("c") categories: String? = null,
        @Query("a") areas: String? = null,
        @Query("i") ingredients: String? = null
    ): FilterDto
}

private const val RANDOM_MEAL = "random.php"
private const val CATEGORIES = "categories.php"
private const val SEARCH_MEALS = "search.php"
private const val MEAL_BY_ID = "lookup.php"
private const val GENERAL_LIST = "list.php"
private const val GENERAL_FILTER = "filter.php"
