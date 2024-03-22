package com.barquero.rickandmorty.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barquero.rickandmorty.presentation.navigation.Graphs
import com.barquero.rickandmorty.presentation.navigation.Page
import com.barquero.rickandmorty.presentation.ui.characterdetail.CharacterDetailViewModel
import com.barquero.rickandmorty.presentation.ui.characterssearch.SearchViewModel
import com.barquero.rickandmorty.presentation.ui.navigationbar.NavigationBarNestedGraph
import com.barquero.rickandmorty.presentation.ui.navigationbar.NavigationBarScreen
import com.barquero.rickandmorty.presentation.ui.pages.CharacterDetailPage
import com.barquero.rickandmorty.presentation.ui.pages.SearchCharacterPage
import com.barquero.rickandmorty.presentation.util.composableHorizontalSlide

@Composable
fun MainGraph(
    mainNavController: NavHostController,
    darkMode: Boolean,
    onThemeUpdated: () -> Unit
) {
    NavHost(
        navController = mainNavController,
        startDestination = Page.NavigationBar.route,
        route = Graphs.GRAPH_ROUTE_MAIN.name
    ) {
        composableHorizontalSlide(route = Page.NavigationBar.route) { backStack ->
            val nestedNavController = rememberNavController()
            NavigationBarScreen(
//                sharedViewModel = backStack.sharedViewModel(navController = mainNavController),
                mainRouter = MainRouter(mainNavController),
                darkMode = darkMode,
                onThemeUpdated = onThemeUpdated,
                nestedNavController = nestedNavController
            ) {
                NavigationBarNestedGraph(
                    navController = nestedNavController,
                    mainNavController = mainNavController,
                    parentRoute = Graphs.GRAPH_ROUTE_MAIN.name
                )
            }
        }

        composableHorizontalSlide(route = Page.Search.route) {
            SearchCharacterPage(mainNavController = mainNavController, viewModel = hiltViewModel<SearchViewModel>() )
        }

        composableHorizontalSlide(
            route = "${Page.CharacterDetail.route}/{${Page.CharacterDetail.CHARACTER_ID}}",
            arguments = listOf(
                navArgument(Page.CharacterDetail.CHARACTER_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) {
            CharacterDetailPage(
                mainNavController = mainNavController,
                viewModel = hiltViewModel<CharacterDetailViewModel>()
            )
        }
    }
}