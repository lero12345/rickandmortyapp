package com.barquero.rickandmorty.presentation.navigation

sealed class Page(val route: String) {
    data object NavigationBar : Page("navigation_bar")
    data object Characters : Page("characters")
    data object Favorites : Page("favorites")
    data object Search : Page("search")
    data object CharacterDetail: Page("character_detail") {
        const val CHARACTER_ID: String = "character_id"
    }
}

enum class Graphs {
    GRAPH_ROUTE_MAIN,
}