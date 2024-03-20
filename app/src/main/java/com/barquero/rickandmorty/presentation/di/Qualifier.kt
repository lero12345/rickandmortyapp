package com.barquero.rickandmorty.presentation.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AppSettingsSharedPreference
