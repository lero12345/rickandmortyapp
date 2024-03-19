package com.barquero.rickandmorty.ui.main

import androidx.navigation.NavHostController
import com.barquero.rickandmorty.ui.navigation.Page

class MainRouter(
    private val mainNavController: NavHostController
) {

    fun navigateToSearch() {
        mainNavController.navigate(Page.Search.route)
    }

    fun navigateToMovieDetails(movieId: Int) {
        mainNavController.navigate(Page.MovieDetails.route + "/${movieId}")
    }
}