package com.example.leftoverflow.presentation.filters

import com.example.leftoverflow.domain.model.FilterByCategory

data class FilterUiState(
    val categories: List<FilterByCategory> = emptyList()
)