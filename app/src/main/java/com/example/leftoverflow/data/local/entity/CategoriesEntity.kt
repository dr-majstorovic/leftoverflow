package com.example.leftoverflow.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leftoverflow.utils.CATEGORIES_TABLE

@Entity(tableName = CATEGORIES_TABLE)
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = false) val idMeal: String,
    @ColumnInfo val strName: String,
    @ColumnInfo val strThumb: String
)