package com.example.myprofile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvPopularList(
    val firstAirDate: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val id: Int,
    val isFavorite: Boolean
): Parcelable
