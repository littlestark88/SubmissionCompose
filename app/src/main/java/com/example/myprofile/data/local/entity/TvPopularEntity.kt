package com.example.myprofile.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TvPopularTable")
data class TvPopularEntity(
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

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("isFavorite")
    var isFavorite: Boolean? = false
)
