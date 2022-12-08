package com.example.myprofile.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myprofile.presentation.about.AboutScreen
import com.example.myprofile.presentation.detail.TvPopularDetailScreen
import com.example.myprofile.presentation.favorite.FavoriteScreen
import com.example.myprofile.presentation.search.SearchTvPopularScreen
import com.example.myprofile.presentation.tvpopular.TvPopularScreen
import com.example.myprofile.presentation.ui.component.BottomBar
import com.example.myprofile.presentation.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TvPopularApps(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.TvPopularDetail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.TvPopular.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.TvPopular.route) {
                TvPopularScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.TvPopularDetail.createRoute(it))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.TvPopularDetail.createRoute(it))
                    }
                )
            }
            composable(Screen.Search.route) {
                SearchTvPopularScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.TvPopularDetail.createRoute(it))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.TvPopularDetail.route,
                arguments = listOf(navArgument("movieId") {
                    type = NavType.IntType
                }),
            ) {
                val movieId = it.arguments?.getInt("movieId")
                TvPopularDetailScreen(
                    movieId = movieId ?: 0,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}