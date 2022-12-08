package com.example.myprofile.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myprofile.R
import com.example.myprofile.domain.model.TvPopularList
import com.example.myprofile.presentation.ui.component.TvPopularItem
import com.example.myprofile.presentation.ui.theme.blue
import com.example.myprofile.presentation.ui.theme.lightBlue

@Composable
fun SearchTvPopularContent(
    searchMovie: String,
    tvPopularList: List<TvPopularList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    getSearchText: (String) -> Unit,
) {
    Column {
        Column {
            Text(
                text = stringResource(R.string.label_search_movie),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                value = searchMovie,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { getSearchText(it) },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                trailingIcon = {
                    if (searchMovie.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    getSearchText("")
                                }
                        )
                    }

                }
            )
        }
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(tvPopularList) { data ->
                TvPopularItem(
                    image = data.posterPath,
                    title = data.name,
                    description = data.overview,
                    date = data.firstAirDate,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }

}