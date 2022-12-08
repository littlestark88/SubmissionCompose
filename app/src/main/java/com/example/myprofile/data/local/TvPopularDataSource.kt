package com.example.myprofile.data.local

import com.example.myprofile.data.local.dao.TvPopularDao
import com.example.myprofile.data.local.entity.TvPopularEntity
import kotlinx.coroutines.flow.Flow

class TvPopularDataSource(private val tvPopularDao: TvPopularDao) {

    fun getTvPopular(): Flow<List<TvPopularEntity>> = tvPopularDao.getTvPopular()

    suspend fun insertTvPopular(tvPopularList: List<TvPopularEntity>) = tvPopularDao.insertTvPopular(tvPopularList)

    suspend fun setFavoriteTvPopular(tvPopularEntity: TvPopularEntity, newState: Boolean){
        tvPopularEntity.isFavorite = newState
        tvPopularDao.updateTvPopular(tvPopularEntity)
    }

    fun getFavoriteTvPopular(): Flow<List<TvPopularEntity>> = tvPopularDao.getFavoriteTvPopular()

    fun getTvPopularById(movieId: Int): Flow<TvPopularEntity> = tvPopularDao.getTvPopularById(movieId)

    fun getTvPopularBySearch(movieTitle: String): Flow<List<TvPopularEntity>> = tvPopularDao.getTvPopularBySearch(movieTitle)
}