package com.example.myprofile.data

import com.example.myprofile.base.ApiResponse
import com.example.myprofile.base.NetworkBoundResource
import com.example.myprofile.base.UIState
import com.example.myprofile.data.local.TvPopularDataSource
import com.example.myprofile.data.remote.TvPopularRemoteSource
import com.example.myprofile.data.remote.response.TvPopularListItem
import com.example.myprofile.domain.ITvPopularRepository
import com.example.myprofile.domain.model.TvPopularList
import com.example.myprofile.utils.TvPopularDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvPopularRepository(
    private val tvPopularRemoteSource: TvPopularRemoteSource,
    private val tvPopularDataSource: TvPopularDataSource
): ITvPopularRepository {

    override fun getTvPopular(): Flow<UIState<List<TvPopularList>>> =
        object : NetworkBoundResource<List<TvPopularList>, List<TvPopularListItem>>() {
            override fun loadFromDB(): Flow<List<TvPopularList>> {
                return tvPopularDataSource.getTvPopular().map {
                    TvPopularDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvPopularList>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvPopularListItem>>> =
                tvPopularRemoteSource.getTvPopular()

            override suspend fun saveCallResult(data: List<TvPopularListItem>) {
                val tvPopularList = TvPopularDataMapper.mapResponseToEntities(data)
                tvPopularDataSource.insertTvPopular(tvPopularList)
            }
        }.asFlow()

    override suspend fun setFavoriteTvPopular(tvPopularList: TvPopularList, state: Boolean) {
        val tvPopularEntity = TvPopularDataMapper.mapTvPopularDomainToEntity(tvPopularList)
        tvPopularDataSource.setFavoriteTvPopular(tvPopularEntity, state)
    }

    override fun getFavoriteTvPopular(): Flow<List<TvPopularList>> {
        return tvPopularDataSource.getFavoriteTvPopular().map {
            TvPopularDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getTvPopularById(movieId: Int): Flow<TvPopularList> {
        return tvPopularDataSource.getTvPopularById(movieId).map {
            TvPopularDataMapper.mapTvPopularEntityToDomain(it)
        }
    }

    override fun getTvPopularBySearch(movieTitle: String): Flow<List<TvPopularList>> {
        return tvPopularDataSource.getTvPopularBySearch(movieTitle).map {
            TvPopularDataMapper.mapEntitiesToDomain(it)
        }
    }
}