package com.example.myprofile.domain

import com.example.myprofile.base.UIState
import com.example.myprofile.domain.model.TvPopularList
import kotlinx.coroutines.flow.Flow

interface ITvPopularUseCase {
    fun getTvPopular(): Flow<UIState<List<TvPopularList>>>
    suspend fun setFavoriteTvPopular(tvPopularList: TvPopularList, state: Boolean)
    fun getFavoriteTvPopular(): Flow<List<TvPopularList>>
    fun getTvPopularById(movieId: Int): Flow<TvPopularList>
    fun getTvPopularBySearch(movieTitle: String): Flow<List<TvPopularList>>
}