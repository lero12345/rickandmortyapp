package com.barquero.rickandmorty.presentation

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.barquero.rickandmorty.data.util.DispatchersProvider
import com.barquero.rickandmorty.data.util.DispatchersProviderImpl
import com.barquero.rickandmorty.presentation.di.AppSettingsSharedPreference
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class App: Application() {



}