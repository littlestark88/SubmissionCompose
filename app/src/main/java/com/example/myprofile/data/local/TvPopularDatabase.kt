package com.example.myprofile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myprofile.data.local.dao.TvPopularDao
import com.example.myprofile.data.local.entity.TvPopularEntity

@Database(
    entities = [
        TvPopularEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TvPopularDatabase: RoomDatabase() {

    abstract fun tvPopularDao(): TvPopularDao
}