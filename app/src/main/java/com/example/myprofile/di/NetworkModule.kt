package com.example.myprofile.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.myprofile.BuildConfig.BASE_URL
import com.example.myprofile.data.TvPopularRepository
import com.example.myprofile.data.local.TvPopularDataSource
import com.example.myprofile.data.local.TvPopularDatabase
import com.example.myprofile.data.remote.TvPopularRemoteSource
import com.example.myprofile.data.remote.network.ApiService
import com.example.myprofile.domain.ITvPopularRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val chuckerCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        val chuckerInteractor = ChuckerInterceptor.Builder(androidContext())
            .collector(chuckerCollector)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(chuckerInteractor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<TvPopularDatabase>().tvPopularDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TvPopularDatabase::class.java, "TvPopular.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { TvPopularDataSource(get()) }
    single { TvPopularRemoteSource(get()) }
    factory<ITvPopularRepository> { TvPopularRepository(get(), get()) }
}