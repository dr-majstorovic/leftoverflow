package com.example.leftoverflow.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.leftoverflow.data.local.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM CATEGORIES_TABLE")
    fun getCategories(): Flow<List<CategoriesEntity>>

    @Upsert
    suspend fun upsertAll(categories: List<CategoriesEntity>)
}