package com.example.myprofile.data.remote.network

import com.example.myprofile.BuildConfig.API_KEY
import com.example.myprofile.data.remote.response.TvPopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    suspend fun getTvPopular (
        @Query("api_key") apiKey: String? = API_KEY
    ): TvPopularResponse
}