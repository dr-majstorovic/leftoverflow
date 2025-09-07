package com.example.leftoverflow.data.repository

import androidx.room.withTransaction
import com.example.leftoverflow.data.local.LeftoverflowDataBase
import com.example.leftoverflow.data.local.entity.CategoriesEntity
import com.example.leftoverflow.data.remote.LeftoverflowApiService
import com.example.leftoverflow.domain.mapper.asDomain
import com.example.leftoverflow.domain.mapper.asEntity
import com.example.leftoverflow.domain.model.CategoriesMeal
import com.example.leftoverflow.domain.model.MinimalMeal
import com.example.leftoverflow.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: LeftoverflowApiService,
    private val dataBase: LeftoverflowDataBase
): HomeRepository {

    private val categoriesDao = dataBase.categoriesDao()

    override suspend fun fetchRandomMeal(): List<MinimalMeal> =
        api.getRandomMeal()
            .asDomain()

    /* ----- Categories methods ----- */

    /**
     * The following methods are used to retrieve and cache data within a local database.
     *
     * The cacheCategories() method makes a call to the API and,
     * if the database is empty, stores the data.
     *
     * The fetchCategories() method displays the stored items on the screen if
     * they are stored in the database.
     */
    override fun fetchCategories(): Flow<List<CategoriesMeal>> =
        categoriesDao.getCategories()
            .map { it.map(CategoriesEntity::asDomain) }
            .onEach {
                if (it.isEmpty()) {
                    cacheCategories()
                }
            }
            .flowOn(Dispatchers.IO)

    private suspend fun cacheCategories() {
        api.getCategories()
            .asEntity()
            .also {
                dataBase.withTransaction {
                    categoriesDao.upsertAll(it.orEmpty())
                }
            }
    }
}