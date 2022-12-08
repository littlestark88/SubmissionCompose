package com.example.myprofile.domain

import com.example.myprofile.base.UIState
import com.example.myprofile.domain.model.TvPopularList
import kotlinx.coroutines.flow.Flow

class TvPopularInteractor(private val tvPopularRepository: ITvPopularRepository): ITvPopularUseCase {
    override fun getTvPopular(): Flow<UIState<List<TvPopularList>>> = tvPopularRepository.getTvPopular()
    override suspend fun setFavoriteTvPopular(tvPopularList: TvPopularList, state: Boolean) = tvPopularRepository.setFavoriteTvPopular(tvPopularList, state)
    override fun getFavoriteTvPopular(): Flow<List<TvPopularList>> = tvPopularRepository.getFavoriteTvPopular()
    override fun getTvPopularById(movieId: Int) = tvPopularRepository.getTvPopularById(movieId)
    override fun getTvPopularBySearch(movieTitle: String): Flow<List<TvPopularList>> = tvPopularRepository.getTvPopularBySearch(movieTitle)
}