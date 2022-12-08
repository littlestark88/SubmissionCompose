package com.example.myprofile.data.remote

import android.util.Log
import com.example.myprofile.base.ApiResponse
import com.example.myprofile.data.remote.network.ApiService
import com.example.myprofile.data.remote.response.TvPopularListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvPopularRemoteSource(private val apiService: ApiService) {

    fun getTvPopular(): Flow<ApiResponse<List<TvPopularListItem>>> {
        return flow {
            try {
                val response = apiService.getTvPopular()
                val dataList = response.tvPopularListItem
                if(dataList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.tvPopularListItem))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getTvPopular: ", e.toString() )
            }
        }.flowOn(Dispatchers.IO)
    }
}