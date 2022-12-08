package com.example.myprofile.presentation.tvpopular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myprofile.base.UIState
import com.example.myprofile.presentation.ui.component.EmptyState
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvPopularScreen(
    modifier: Modifier = Modifier,
    tvPopularViewModel: TvPopularViewModel = koinViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    tvPopularViewModel.getTvPopular.collectAsState(
        initial = UIState.Loading()
    ).value.let {
        when (it) {
            is UIState.Loading -> {
                tvPopularViewModel.getTvPopular()
            }
            is UIState.Empty -> {
                EmptyState()
            }
            is UIState.Success -> {
                it.data?.let { dataList ->
                    TvPopularContent(
                        tvPopularList = dataList,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
            is UIState.Error -> {
                it.message
            }
        }
    }
}