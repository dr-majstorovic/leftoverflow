package com.example.leftoverflow.di

import android.content.Context
import androidx.room.Room
import com.example.leftoverflow.data.local.LeftoverflowDataBase
import com.example.leftoverflow.data.local.dao.BookmarkDao
import com.example.leftoverflow.data.local.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DATA_BASE_NAME = "leftoverflow_db"

    @Provides
    @Singleton
    fun providesRoomDataBase(
        @ApplicationContext context: Context
    ): LeftoverflowDataBase {

        return Room.databaseBuilder(
            context = context,
            klass = LeftoverflowDataBase::class.java,
            name = DATA_BASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun providesBookmarkDao(dataBase: LeftoverflowDataBase): BookmarkDao {
        return dataBase.bookmarkDao()
    }

    @Provides
    @Singleton
    fun providesRecipeDao(dataBase: LeftoverflowDataBase): RecipeDao {
        return dataBase.recipeDao()
    }
}