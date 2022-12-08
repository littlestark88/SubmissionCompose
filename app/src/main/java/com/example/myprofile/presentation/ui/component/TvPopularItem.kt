package com.example.myprofile.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myprofile.presentation.ui.theme.MyProfileTheme
import com.example.myprofile.presentation.ui.theme.Shapes

@Composable
fun TvPopularItem(
    image: String,
    title: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = loadImage(urlImage = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow =TextOverflow.Ellipsis,
                fontWeight = FontWeight.W600,
            )
            Text(
                text = date,
                overflow =TextOverflow.Ellipsis,
                fontWeight = FontWeight.W100,
            )
            Text(
                text = description,
                overflow =TextOverflow.Ellipsis,
                fontWeight = FontWeight.W300,
                maxLines = 2
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TvPopularPreview() {
    MyProfileTheme {
        TvPopularItem(image = "/cvhNj9eoRBe5SxjCbQTkh05UP5K.jpg", title = "Rick and Morty", description = "Rick is a mentally-unbalanced but scientifically gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.", date = "2013-12-02")
    }
}