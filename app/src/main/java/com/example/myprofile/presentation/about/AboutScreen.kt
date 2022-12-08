package com.example.myprofile.presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myprofile.R

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.pp),
            contentDescription = stringResource(R.string.label_photo_profile),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.label_name_profile),
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.h6,
        )
        Text(
            text = stringResource(R.string.label_email_profile),
            style = MaterialTheme.typography.h6,
        )
    }
}