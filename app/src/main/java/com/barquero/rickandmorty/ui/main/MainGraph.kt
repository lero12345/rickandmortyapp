package com.barquero.rickandmorty.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barquero.rickandmorty.ui.navigationbar.NavigationBarNestedGraph
import com.barquero.rickandmorty.ui.navigationbar.NavigationBarScreen
import com.barquero.rickandmorty.ui.util.composableHorizontalSlide
import com.barquero.rickandmorty.ui.navigation.Graphs
import com.barquero.rickandmorty.ui.navigation.Page

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
        }

        composableHorizontalSlide(
            route = "${Page.MovieDetails.route}/{${Page.MovieDetails.MOVIE_ID}}",
            arguments = listOf(
                navArgument(Page.MovieDetails.MOVIE_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) {
        }
    }
}