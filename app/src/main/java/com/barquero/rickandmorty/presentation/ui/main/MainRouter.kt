package com.barquero.rickandmorty.presentation.ui.main

import androidx.navigation.NavHostController
import com.barquero.rickandmorty.presentation.navigation.Page

class MainRouter(
    private val mainNavController: NavHostController
) {

    fun navigateToSearch() {
        mainNavController.navigate(Page.Search.route)
    }

    fun navigateToCharacterDetails(characterId: Int) {
        mainNavController.navigate(Page.CharacterDetail.route + "/${characterId}")
    }
}