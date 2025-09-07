package com.example.leftoverflow.presentation.bookmark

import com.example.leftoverflow.domain.model.Meal

data class BookmarkUiState(
    val allBookmarks: List<Meal> = emptyList()
)