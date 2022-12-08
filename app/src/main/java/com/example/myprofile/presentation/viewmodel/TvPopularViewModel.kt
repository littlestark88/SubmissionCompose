package com.example.myprofile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.base.UIState
import com.example.myprofile.domain.ITvPopularUseCase
import com.example.myprofile.domain.model.TvPopularList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TvPopularViewModel(private val tvPopularUseCase: ITvPopularUseCase): ViewModel() {

    private val _getTvPopular: MutableStateFlow<UIState<List<TvPopularList>>> = MutableStateFlow(UIState.Loading())
    val getTvPopular: StateFlow<UIState<List<TvPopularList>>> get() = _getTvPopular

    private val _getTvPopularById: MutableStateFlow<UIState<TvPopularList>> = MutableStateFlow(UIState.Loading())
    val getTvPopularById: StateFlow<UIState<TvPopularList>> get() = _getTvPopularById

    private val _getTvPopularBySearch: MutableStateFlow<UIState<List<TvPopularList>>> = MutableStateFlow(UIState.Loading())
    val getTvPopularBySearch: StateFlow<UIState<List<TvPopularList>>> get() = _getTvPopularBySearch

    private val _getFavoriteTvPopular: MutableStateFlow<UIState<List<TvPopularList>>> = MutableStateFlow(UIState.Loading())
    val getFavoriteTvPopular: StateFlow<UIState<List<TvPopularList>>> get() = _getFavoriteTvPopular

    private val _updateTvPopular: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Loading())
    val updateTvPopular: StateFlow<UIState<Unit>> get() = _updateTvPopular


    fun getTvPopular() {
        viewModelScope.launch {
            tvPopularUseCase.getTvPopular()
                .collect{
                    if(it.data?.isNotEmpty() == true) {
                        _getTvPopular.value = UIState.Success(it.data)
                    } else {
                        _getTvPopular.value = UIState.Empty()
                    }
            }
        }
    }

    fun getTvPopularById(movieId: Int) {
        viewModelScope.launch {
            tvPopularUseCase.getTvPopularById(movieId)
                .collect{
                        _getTvPopularById.value = UIState.Success(it)
                }
        }
    }

    fun getTvPopularBySearch(movieTitle: String) {
        viewModelScope.launch {
            tvPopularUseCase.getTvPopularBySearch(movieTitle)
                .collect{
                    if(it.isEmpty()) {
                        getTvPopularBySearch("")
                    } else {
                        _getTvPopularBySearch.value = UIState.Success(it)
                    }
                }
        }
    }

    fun getFavoriteTvPopular() {
        viewModelScope.launch {
            tvPopularUseCase.getFavoriteTvPopular()
                .collect{
                    if(it.isEmpty()) {

                        _getFavoriteTvPopular.value = UIState.Empty()
                    } else {

                        _getFavoriteTvPopular.value = UIState.Success(it)
                    }
                }
        }
    }

    fun updateTvPopular(data: TvPopularList, state: Boolean) {
        viewModelScope.launch {
            tvPopularUseCase.setFavoriteTvPopular(data, state)
        }
    }
}