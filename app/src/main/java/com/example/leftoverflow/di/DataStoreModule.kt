package com.example.leftoverflow.di

import android.content.Context
import com.example.leftoverflow.data.datastore.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesSettingsDataStore(
        @ApplicationContext context: Context
    ) = SettingsDataStore(context)
}