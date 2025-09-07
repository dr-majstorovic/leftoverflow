package com.example.leftoverflow.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.leftoverflow.data.local.entity.MinimalMealEntity

@Dao
interface MinimalMealDao {

    @Query("SELECT * FROM SEARCH_TABLE WHERE strMeal LIKE '%' || :query || '%'")
    fun pagingSource(query: String): PagingSource<Int, MinimalMealEntity>

    @Upsert
    suspend fun upsertData(entity: List<MinimalMealEntity>)

    @Query("DELETE FROM SEARCH_TABLE")
    suspend fun clearData()
}