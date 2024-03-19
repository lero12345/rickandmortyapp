package com.barquero.rickandmorty.ui.navigation

sealed class Page(val route: String) {
    data object NavigationBar : Page("navigation_bar")
    data object Characters : Page("characters")
    data object Favorites : Page("favorites")
    data object Search : Page("search")
    data object MovieDetails : Page("movie_details") {
        const val MOVIE_ID: String = "movieId"
    }
}

enum class Graphs {
    GRAPH_ROUTE_MAIN,
}