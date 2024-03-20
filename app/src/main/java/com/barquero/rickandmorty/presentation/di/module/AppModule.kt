package com.barquero.rickandmorty.presentation.di.module

import android.content.Context
import android.content.SharedPreferences
import com.barquero.rickandmorty.data.util.DispatchersProvider
import com.barquero.rickandmorty.data.util.DispatchersProviderImpl
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

}