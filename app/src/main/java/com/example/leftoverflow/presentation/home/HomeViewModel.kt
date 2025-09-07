package com.example.leftoverflow.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.leftoverflow.domain.model.CategoriesMeal
import com.example.leftoverflow.domain.model.MinimalMeal
import com.example.leftoverflow.domain.usecase.CategoriesUseCase
import com.example.leftoverflow.domain.usecase.RandomMealUseCase
import com.example.leftoverflow.domain.usecase.SearchMealUseCase
import com.example.leftoverflow.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    categoriesUseCase: CategoriesUseCase,
    private val randomMealUseCase: RandomMealUseCase,
    private val searchMealUseCase: SearchMealUseCase
): ViewModel() {

    private val _randomFlow: MutableStateFlow<Resource<MinimalMeal>> =
        MutableStateFlow(Resource.Loading())
    val randomFlow: StateFlow<Resource<MinimalMeal>> = _randomFlow.asStateFlow()

    private val _searchByLetterState: MutableStateFlow<Flow<PagingData<MinimalMeal>>> =
        MutableStateFlow(emptyFlow())
    val searchByLetterState = _searchByLetterState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        val abc = ('a'..'z').toList().filterNot {
            it in listOf('ñ')
        }

        mealByLetter(letter = abc.random().toString())
        randomMeal()
    }

    val categoriesFlow: StateFlow<List<CategoriesMeal>> =
        categoriesUseCase.invoke()
            .map { it }
            .catch { emit(emptyList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    private fun mealByLetter(letter: String = "") {
        _searchByLetterState.update {
            searchMealUseCase.invoke(query = letter)
                .cachedIn(viewModelScope)
                .catch { emit(PagingData.empty()) }
        }
    }

    private fun randomMeal() {
        viewModelScope.launch {
            _randomFlow.value = Resource.Loading()

            _randomFlow.update {
                try {
                    Resource.Success(data = randomMealUseCase.invoke().first())
                } catch (e: HttpException) {
                    Resource.Error(e.message())
                } catch (e: IOException) {
                    Resource.Error(message = e.toString())
                }
            }
        }
    }

    fun refreshAll() {
        _isRefreshing.update { true }

        viewModelScope.launch {
            delay(1500)
            randomMeal()
            mealByLetter()
            _isRefreshing.update { false }
        }
    }
}