package com.example.myprofile.data.local.dao

import androidx.room.*
import com.example.myprofile.data.local.entity.TvPopularEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvPopularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvPopular(tvPopularEntity: List<TvPopularEntity>)

    @Query("SELECT * FROM TvPopularTable")
    fun getTvPopular(): Flow<List<TvPopularEntity>>

    @Query("DELETE FROM TvPopularTable")
    suspend fun removeAllTvPopular()

    @Update
    suspend fun updateTvPopular(tvPopularEntity: TvPopularEntity)

    @Query("SELECT * FROM TvPopularTable where isFavorite = 1")
    fun getFavoriteTvPopular(): Flow<List<TvPopularEntity>>

    @Query("SELECT * FROM TvPopularTable where id = :movieId")
    fun getTvPopularById(movieId: Int): Flow<TvPopularEntity>

    @Query("SELECT * FROM TvPopularTable where name LIKE '%' || :movieTitle || '%' ORDER BY id DESC")
    fun getTvPopularBySearch(movieTitle: String): Flow<List<TvPopularEntity>>
}