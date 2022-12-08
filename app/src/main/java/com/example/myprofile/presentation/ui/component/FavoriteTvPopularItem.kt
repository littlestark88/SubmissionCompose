package com.example.myprofile.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myprofile.R
import com.example.myprofile.domain.model.TvPopularList
import com.example.myprofile.presentation.detail.setFavoriteTvPopular
import com.example.myprofile.presentation.ui.theme.MyProfileTheme
import com.example.myprofile.presentation.ui.theme.Shapes
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteTvPopularItem(
    data: TvPopularList,
    modifier: Modifier = Modifier
) {
    var favoriteIcon by remember {
        mutableStateOf(data.isFavorite)
    }
    val tvPopularViewModel: TvPopularViewModel = koinViewModel()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = loadImage(urlImage = data.posterPath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
                .size(150.dp)
        ) {
            Text(
                text = data.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W600,
            )
            Text(
                text = data.firstAirDate,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W100,
            )
            Text(
                text = data.overview,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W300,
                maxLines = 2
            )
        }
        IconButton(
            onClick = {
                setFavoriteTvPopular(data, tvPopularViewModel)
                favoriteIcon = !favoriteIcon
            },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = if (favoriteIcon) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.label_favorite),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16f)
                    ),
                tint = Color.Red,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FavoriteTvPopularPreview() {
    MyProfileTheme {
        TvPopularItem(
            image = "/cvhNj9eoRBe5SxjCbQTkh05UP5K.jpg",
            title = "Rick and Morty",
            description = "Rick is a mentally-unbalanced but scientifically gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
            date = "2013-12-02"
        )
    }
}