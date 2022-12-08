package com.example.myprofile.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myprofile.BuildConfig
import com.example.myprofile.R

@Composable
fun loadImage(
    urlImage: String,
): Painter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(BuildConfig.POSTER_URL + urlImage)
            .placeholder(R.drawable.ic_refresh)
            .build()
    )
}
