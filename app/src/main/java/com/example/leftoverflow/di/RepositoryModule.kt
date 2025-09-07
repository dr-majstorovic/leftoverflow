package com.example.leftoverflow.di

import com.example.leftoverflow.data.repository.BookmarkRepositoryImpl
import com.example.leftoverflow.data.repository.FiltersRepositoryImpl
import com.example.leftoverflow.data.repository.HomeRepositoryImpl
import com.example.leftoverflow.data.repository.MealDetailRepositoryImpl
import com.example.leftoverflow.data.repository.RecipeRepositoryImpl
import com.example.leftoverflow.data.repository.SearchMealRepositoryImpl
import com.example.leftoverflow.domain.repository.BookmarkRepository
import com.example.leftoverflow.domain.repository.FiltersRepository
import com.example.leftoverflow.domain.repository.HomeRepository
import com.example.leftoverflow.domain.repository.MealDetailRepository
import com.example.leftoverflow.domain.repository.RecipeRepository
import com.example.leftoverflow.domain.repository.SearchMealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindSearchMealRepository(impl: SearchMealRepositoryImpl): SearchMealRepository

    @Binds
    abstract fun bindMealDetailRepository(impl: MealDetailRepositoryImpl): MealDetailRepository

    @Binds
    abstract fun bindBookmarkRepository(impl: BookmarkRepositoryImpl): BookmarkRepository

    @Binds
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    abstract fun bindFiltersRepository(impl: FiltersRepositoryImpl): FiltersRepository
}