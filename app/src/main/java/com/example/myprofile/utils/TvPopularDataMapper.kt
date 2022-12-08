package com.example.myprofile.utils

import com.example.myprofile.data.local.entity.TvPopularEntity
import com.example.myprofile.data.remote.response.TvPopularListItem
import com.example.myprofile.domain.model.TvPopularList

object TvPopularDataMapper {
    fun mapResponseToEntities(data: List<TvPopularListItem>): List<TvPopularEntity> {
        val dataList = ArrayList<TvPopularEntity>()
        data.map {
            val tvPopular = TvPopularEntity(
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                name = it.name,
                id = it.id ?: 0,
                isFavorite = false
            )
            dataList.add(tvPopular)
        }
        return dataList
    }

    fun mapEntitiesToDomain(data: List<TvPopularEntity>): List<TvPopularList> =
        data.map {
            TvPopularList(
                firstAirDate = it.firstAirDate.orEmpty(),
                overview = it.overview.orEmpty(),
                posterPath = it.posterPath.orEmpty(),
                backdropPath = it.backdropPath.orEmpty(),
                popularity = it.popularity ?: 0.0,
                voteAverage = it.voteAverage ?: 0.0,
                name = it.name.orEmpty(),
                id = it.id,
                isFavorite = it.isFavorite ?: false
            )
        }

    fun mapTvPopularDomainToEntity(data: TvPopularList) = TvPopularEntity(
        firstAirDate = data.firstAirDate,
        overview = data.overview,
        posterPath = data.posterPath,
        backdropPath = data.backdropPath,
        popularity = data.popularity,
        voteAverage = data.voteAverage,
        name = data.name,
        id = data.id,
        isFavorite = true
    )

    fun mapTvPopularEntityToDomain(data: TvPopularEntity) = TvPopularList(
        firstAirDate = data.firstAirDate.orEmpty(),
        overview = data.overview.orEmpty(),
        posterPath = data.posterPath.orEmpty(),
        backdropPath = data.backdropPath.orEmpty(),
        popularity = data.popularity ?: 0.0,
        voteAverage = data.voteAverage ?: 0.0,
        name = data.name.orEmpty(),
        id = data.id,
        isFavorite = data.isFavorite ?: false
    )
}