package com.barquero.rickandmorty.presentation.di.module

import android.content.Context
import android.content.SharedPreferences
import com.barquero.rickandmorty.data.api.RickAndMortyService
import com.barquero.rickandmorty.data.datasource.CharacterRemoteDataSource
import com.barquero.rickandmorty.data.repository.CharacterRepository
import com.barquero.rickandmorty.data.repository.CharacterRepositoryImpl
import com.barquero.rickandmorty.data.util.DispatchersProvider
import com.barquero.rickandmorty.data.util.DispatchersProviderImpl
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCase
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCaseImpl
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
}