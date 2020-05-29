package com.nurlandroid.kotapp.di

import com.nurlandroid.kotapp.common.CustomFragmentFactory
import com.nurlandroid.kotapp.NetworkApi
import com.nurlandroid.kotapp.feature.posts.PostViewModel
import com.nurlandroid.kotapp.experimental.networkbound.PostsRepository
import com.nurlandroid.kotapp.feature.posts.PostRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val diModule = module {
    single { CustomFragmentFactory() }
    viewModel { PostViewModel(get()) }

    single { PostRepository(get()) }
    single { PostsRepository(get(), get()) }

    single<NetworkApi> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }
//
//    single { PostDatabase.getDatabase(androidContext()) }
}