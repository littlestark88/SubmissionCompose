package com.example.myprofile.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myprofile.R

@Composable
fun EmptyState(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.Url("https://assets1.lottiefiles.com/packages/lf20_hl5n0bwb.json"))
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(250.dp)
        )
        Text(
            text = stringResource(R.string.label_data_empty),
            modifier = Modifier
                .padding(16.dp),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.W600,
        )
    }
}