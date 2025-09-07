package com.example.leftoverflow.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leftoverflow.domain.model.Meal
import com.example.leftoverflow.domain.usecase.DeleteBookmarkUseCase
import com.example.leftoverflow.domain.usecase.GetBookmarkByIdUseCase
import com.example.leftoverflow.domain.usecase.IsBookmarkedUseCase
import com.example.leftoverflow.domain.usecase.SaveBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailOfflineViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mealByIdUseCase: GetBookmarkByIdUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
): ViewModel() {

    private val mealId = checkNotNull(savedStateHandle.get<String>("mealId"))

    private val _mealById: MutableStateFlow<Meal?> = MutableStateFlow(Meal())
    val mealById: StateFlow<Meal?> = _mealById.asStateFlow()

    init {
        Log.d("MealDetailOfflineViewModel", "MealId: $mealId")

        viewModelScope.launch {
            mealByIdUseCase.invoke(mealId)
                .collect { data ->
                    _mealById.value = data
                }
        }
    }

    fun isBookmarked(): Flow<Boolean> = isBookmarkedUseCase.invoke(mealId)

    fun saveToBookmark(meal: Meal) =
        viewModelScope.launch {
            saveBookmarkUseCase.invoke(meal)
        }

    fun deleteFromBookmark(meal: Meal) =
        viewModelScope.launch {
            deleteBookmarkUseCase.invoke(meal)
        }
}