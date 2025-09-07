package com.example.leftoverflow.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leftoverflow.utils.SEARCH_TABLE

@Entity(tableName = SEARCH_TABLE)
data class MinimalMealEntity(
    @PrimaryKey(autoGenerate = false) val idMeal: String,
    @ColumnInfo val strMeal: String,
    @ColumnInfo val strMealThumb: String,
    @ColumnInfo val strCategory: String
)