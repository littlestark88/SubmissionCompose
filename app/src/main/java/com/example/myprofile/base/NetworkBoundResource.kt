package com.example.myprofile.base

import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<UIState<ResultType>> = flow {
        emit(UIState.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(UIState.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { UIState.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { UIState.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(UIState.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { UIState.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<UIState<ResultType>> = result
}