package com.barquero.rickandmorty.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.barquero.rickandmorty.data.database.AppDatabase
import com.barquero.rickandmorty.data.database.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "characterFavorites.db").build()

    @Provides
    fun providesFavoritesDao(appDatabase: AppDatabase): FavoritesDao = appDatabase.favoritesDao()
}