package com.barquero.rickandmorty.presentation.ui.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.ui.graphics.vector.ImageVector
import com.barquero.rickandmorty.presentation.navigation.Page

data class NavigationBarUiState(
    val bottomItems: List<BottomNavigationBarItem> = listOf(
        BottomNavigationBarItem.Characters,
        BottomNavigationBarItem.Favorites
    )
)

sealed class BottomNavigationBarItem(
    val tabName: String,
    val imageVector: ImageVector,
    val route: String,
) {
    data object Characters : BottomNavigationBarItem("Characters", imageVector = Icons.Default.PersonSearch, Page.Characters.route)
    data object Favorites : BottomNavigationBarItem("Favorites", imageVector = Icons.Default.FavoriteBorder, Page.Favorites.route)
}
