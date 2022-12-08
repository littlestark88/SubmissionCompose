package com.example.myprofile.di


import com.example.myprofile.domain.ITvPopularUseCase
import com.example.myprofile.domain.TvPopularInteractor
import com.example.myprofile.presentation.viewmodel.TvPopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val useCaseModule = module {
    factory<ITvPopularUseCase> { TvPopularInteractor(get()) }
}

val viewModelModule = module {
    viewModelOf(::TvPopularViewModel)
}