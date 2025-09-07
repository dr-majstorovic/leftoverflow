package com.example.leftoverflow.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.leftoverflow.data.local.LeftoverflowDataBase
import com.example.leftoverflow.data.local.entity.MinimalMealEntity
import com.example.leftoverflow.data.remote.LeftoverflowApiService
import com.example.leftoverflow.data.repository.paging.SearchMealMediator
import com.example.leftoverflow.domain.mapper.asDomain
import com.example.leftoverflow.domain.model.MinimalMeal
import com.example.leftoverflow.domain.repository.SearchMealRepository
import com.example.leftoverflow.utils.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchMealRepositoryImpl @Inject constructor(
    private val api: LeftoverflowApiService,
    private val dataBase: LeftoverflowDataBase
): SearchMealRepository {

    private val dao = dataBase.minimalMealDao()

    override fun fetchSearchedMeals(
        query: String,
        //queryByLetter: String?
    ): Flow<PagingData<MinimalMeal>> {
        val pagingSourceFactory = { dao.pagingSource(query) }
        val remoteMediator = SearchMealMediator(
            api = api,
            dataBase = dataBase,
            query = query,
            //queryByLetter = queryByLetter.orEmpty()
        )
        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = remoteMediator
        ).flow
            .map { pagingData -> pagingData.map(MinimalMealEntity::asDomain) }
            .flowOn(Dispatchers.IO)
    }
}