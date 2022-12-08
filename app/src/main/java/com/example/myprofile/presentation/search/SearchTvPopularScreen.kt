package com.example.myprofile.presentation.search

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.myprofile.base.UIState
import com.example.myprofile.presentation.ui.component.EmptyState
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchTvPopularScreen(
    modifier: Modifier = Modifier,
    tvPopularViewModel: TvPopularViewModel = koinViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    var searchMovie by remember { mutableStateOf("") }
    tvPopularViewModel.getTvPopularBySearch(searchMovie)
    tvPopularViewModel.getTvPopularBySearch.collectAsState(
        initial = UIState.Loading()
    ).value.let {
        when (it) {
            is UIState.Loading -> {
            }
            is UIState.Empty -> {
                EmptyState()
            }
            is UIState.Success -> {
                it.data?.let { dataList ->
                    SearchTvPopularContent(
                        searchMovie = searchMovie,
                        tvPopularList = dataList,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    ) { getTextSearch ->
                        searchMovie = getTextSearch
                    }
                }
            }
            is UIState.Error -> {
                it.message
            }
        }
    }
}