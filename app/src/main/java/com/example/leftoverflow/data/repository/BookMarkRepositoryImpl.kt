package com.example.leftoverflow.data.repository

import com.example.leftoverflow.data.local.dao.BookmarkDao
import com.example.leftoverflow.data.local.entity.MealEntity
import com.example.leftoverflow.domain.mapper.asDomain
import com.example.leftoverflow.domain.mapper.asEntity
import com.example.leftoverflow.domain.model.Meal
import com.example.leftoverflow.domain.repository.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
): BookmarkRepository {

    override fun getAllBookmarks(): Flow<List<Meal>> =
        bookmarkDao.getBookmark()
            .map { it.map(MealEntity::asDomain) }
            .flowOn(Dispatchers.IO)

    override fun getBookmarkById(id: String): Flow<Meal?> =
        bookmarkDao.getBookmarkById(id)
            .map { it?.asDomain() }
            .flowOn(Dispatchers.IO)

    override fun isBookmark(id: String): Flow<Boolean> =
        bookmarkDao.isBookmarked(id)
            .flowOn(Dispatchers.IO)

    override suspend fun upsertBookmark(meal: Meal) = withContext(Dispatchers.IO) {
        bookmarkDao.upsertBookmark(meal.asEntity())
    }

    override suspend fun deleteBookmark(meal: Meal) = withContext(Dispatchers.IO) {
        bookmarkDao.deleteBookmark(meal.asEntity())
    }

    override suspend fun clearAll() = withContext(Dispatchers.IO) {
        bookmarkDao.clearAll()
    }
}