package com.example.myprofile.presentation.ui.navigation

sealed class Screen(val route: String) {
    object TvPopular: Screen("TvPopular")
    object Favorite: Screen("Favorite")
    object Search: Screen("Search")
    object About: Screen("About")
    object TvPopularDetail: Screen("TvPopularDetail/{movieId}") {
        fun createRoute(movieId: Int) = "TvPopularDetail/$movieId"
    }
}
