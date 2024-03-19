package com.barquero.rickandmorty.ui.navigationbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.barquero.rickandmorty.ui.navigation.Page
import com.barquero.rickandmorty.ui.util.composableHorizontalSlide

@Composable
fun NavigationBarNestedGraph(
    navController: NavHostController,
    mainNavController: NavHostController,
    parentRoute: String
) {
    NavHost(
        navController = navController,
        startDestination = Page.Characters.route,
        route = parentRoute
    ) {
        composableHorizontalSlide(route = Page.Characters.route) { backStack ->
        }
        composableHorizontalSlide(route = Page.Favorites.route) {
        }
    }
}