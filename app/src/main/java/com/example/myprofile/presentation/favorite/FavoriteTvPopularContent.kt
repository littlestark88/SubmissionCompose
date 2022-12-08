package com.example.myprofile.presentation.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myprofile.domain.model.TvPopularList
import com.example.myprofile.presentation.ui.component.FavoriteTvPopularItem

@Composable
fun FavoriteTvPopularContent(
    tvPopularList: List<TvPopularList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        items(tvPopularList) { data ->
            FavoriteTvPopularItem(
                data = data,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}