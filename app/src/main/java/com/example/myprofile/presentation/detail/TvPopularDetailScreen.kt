package com.example.myprofile.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myprofile.base.UIState
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvPopularDetailScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    tvPopularViewModel: TvPopularViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    tvPopularViewModel.getTvPopularById.collectAsState(
        initial = UIState.Loading()
    ).value.let {
        when(it) {
            is UIState.Loading -> {
                tvPopularViewModel.getTvPopularById(movieId)
            }
            is UIState.Empty -> {
            }
            is UIState.Success -> {
                it.data?.let { data ->
                    TvPopularDetailContent(
                        modifier = modifier,
                        data = data,
                        onBackClick = navigateBack
                    )
                }
            }
            is UIState.Error -> {
                it.message
            }
        }
    }
}