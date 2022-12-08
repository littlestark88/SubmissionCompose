package com.example.myprofile.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvPopularListItem(
    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = 0.0,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = 0.0,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = 0
)
