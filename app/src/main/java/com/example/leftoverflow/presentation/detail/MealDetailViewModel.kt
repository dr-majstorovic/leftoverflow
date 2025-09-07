package com.example.leftoverflow.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leftoverflow.domain.model.Meal
import com.example.leftoverflow.domain.usecase.DeleteBookmarkUseCase
import com.example.leftoverflow.domain.usecase.IsBookmarkedUseCase
import com.example.leftoverflow.domain.usecase.MealDetailUseCase
import com.example.leftoverflow.domain.usecase.SaveBookmarkUseCase
import com.example.leftoverflow.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mealDetailUseCase: MealDetailUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
): ViewModel() {

    private val mealId = checkNotNull(savedStateHandle.get<String>("mealId"))

    private val _detailState: MutableStateFlow<Resource<Meal>> =
        MutableStateFlow(Resource.Loading())
    val detailState: StateFlow<Resource<Meal>> = _detailState.asStateFlow()

    init {
        Log.d("MealDetailViewModel", "MealId: $mealId")

        viewModelScope.launch {
            _detailState.value = Resource.Loading()

            _detailState.update {
                try {
                    Resource.Success(data = mealDetailUseCase.invoke(mealId))
                } catch (e: IOException) {
                    Resource.Error(e.message.toString())
                } catch (e: HttpException) {
                    Resource.Error(e.message())
                }
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