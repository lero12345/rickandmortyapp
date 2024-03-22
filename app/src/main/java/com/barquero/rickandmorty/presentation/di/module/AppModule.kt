package com.barquero.rickandmorty.presentation.di.module

import android.content.Context
import android.content.SharedPreferences
import com.barquero.rickandmorty.data.api.RickAndMortyService
import com.barquero.rickandmorty.data.database.FavoritesDao
import com.barquero.rickandmorty.data.datasource.CharacterRemoteDataSource
import com.barquero.rickandmorty.data.datasource.FavoritesLocalDataSource
import com.barquero.rickandmorty.data.repository.CharacterDetailRepository
import com.barquero.rickandmorty.data.repository.CharacterDetailRepositoryImpl
import com.barquero.rickandmorty.data.repository.CharacterRepository
import com.barquero.rickandmorty.data.repository.CharacterRepositoryImpl
import com.barquero.rickandmorty.data.repository.FavoritesByIdRepository
import com.barquero.rickandmorty.data.repository.FavoritesByIdRepositoryImpl
import com.barquero.rickandmorty.data.repository.FavoritesRepository
import com.barquero.rickandmorty.data.repository.FavoritesRepositoryImpl
import com.barquero.rickandmorty.data.util.DispatchersProvider
import com.barquero.rickandmorty.data.util.DispatchersProviderImpl
import com.barquero.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.barquero.rickandmorty.domain.usecase.GetCharacterDetailUseCaseImpl
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCase
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCaseImpl
import com.barquero.rickandmorty.domain.usecase.GetFavoriteByIdUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoriteByIdUseCaseImpl
import com.barquero.rickandmorty.domain.usecase.GetFavoritesUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoritesUseCaseImpl
import com.barquero.rickandmorty.presentation.di.AppSettingsSharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl
    }

    @Provides
    @AppSettingsSharedPreference
    fun provideAppSettingsSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideCharacterRepository(
        remoteDataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(characterRepository)
    }

    @Provides
    fun provideCharacterRemoteDataSource(
        rickAndMortyService: RickAndMortyService
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSource(rickAndMortyService)
    }

    @Provides
    fun providesFavoritesRepository(
        localDataSource: FavoritesLocalDataSource
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(localDataSource)
    }

    @Provides
    fun provideGetFavoritesUseCase(
        favoritesRepository: FavoritesRepository
    ): GetFavoritesUseCase = GetFavoritesUseCaseImpl(favoritesRepository)

    @Provides
    fun provideGetFavoritesLocalDataSource(
        favoritesDao: FavoritesDao
    ): FavoritesLocalDataSource = FavoritesLocalDataSource(favoritesDao)

    @Provides
    fun providesCharacterDetailRepository(
        remoteDataSource: CharacterRemoteDataSource
    ): CharacterDetailRepository = CharacterDetailRepositoryImpl(remoteDataSource)

    @Provides
    fun providesCharacterDetailUseCase(
        repository: CharacterDetailRepository
    ): GetCharacterDetailUseCase = GetCharacterDetailUseCaseImpl(repository)

    @Provides
    fun providesGetFavoritesByIdUseCase(
        favoritesByIdRepository: FavoritesByIdRepository
    ): GetFavoriteByIdUseCase = GetFavoriteByIdUseCaseImpl(favoritesByIdRepository)

    @Provides
    fun provideFavoritesByIdRepository(
        favoritesLocalDataSource: FavoritesLocalDataSource
    ): FavoritesByIdRepository = FavoritesByIdRepositoryImpl(favoritesLocalDataSource)

}