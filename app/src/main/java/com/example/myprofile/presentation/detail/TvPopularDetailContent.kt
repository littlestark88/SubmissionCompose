package com.example.myprofile.presentation.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myprofile.R
import com.example.myprofile.domain.model.TvPopularList
import com.example.myprofile.presentation.ui.component.loadImage
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvPopularDetailContent(
    data: TvPopularList,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    tvPopularViewModel: TvPopularViewModel = koinViewModel(),
) {
    var favoriteIcon by remember {
        mutableStateOf(data.isFavorite)
    }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box(
                modifier = modifier.height(400.dp)
            ) {
                Image(
                    painter = loadImage(urlImage = data.backdropPath),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.label_back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                        .background(
                            color = Color.Gray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16f)
                        )
                        .padding(4.dp)
                )
                IconButton(
                    onClick = {
                        setFavoriteTvPopular(data, tvPopularViewModel)
                        favoriteIcon = !favoriteIcon
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd)


                ) {
                    Icon(
                        imageVector = if(favoriteIcon) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(R.string.label_favorite),
                        modifier = Modifier
                            .background(
                                color = Color.Gray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(16f)
                            )
                            .padding(4.dp)
                        ,
                        tint = Color.Red,
                    )
                }
                Image(
                    painter = loadImage(urlImage = data.posterPath),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(200.dp)
                        .width(150.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp))
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.label_star),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                        .padding(end = 30.dp, bottom = 52.dp),
                    tint = Color.Red
                )

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.label_popularity),
                    modifier = Modifier

                        .align(Alignment.BottomCenter)
                        .padding(end = 30.dp, bottom = 40.dp),
                    tint = Color.Black
                )
                Text(
                    text = data.voteAverage.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 57.dp, start = 15.dp)
                )
                Text(
                    text = data.popularity.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 28.dp, start = 40.dp)
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = data.firstAirDate,
                    fontWeight = FontWeight.W100,
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = data.overview,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

fun setFavoriteTvPopular(
    data: TvPopularList,
    tvPopularViewModel: TvPopularViewModel,
) {
    var favoriteStateTvPopular = data.isFavorite
    favoriteStateTvPopular = !favoriteStateTvPopular
    tvPopularViewModel.updateTvPopular(data, favoriteStateTvPopular)
}

