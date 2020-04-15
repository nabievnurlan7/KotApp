package com.nurlandroid.kotapp.di

import com.nurlandroid.kotapp.common.CustomFragmentFactory
import com.nurlandroid.kotapp.coroutine.DummyInteractor
import com.nurlandroid.kotapp.coroutine.NetworkApi
import com.nurlandroid.kotapp.feature.MyViewModel
import com.nurlandroid.kotapp.feature.PostDatabase
import com.nurlandroid.kotapp.networkbound.PostsRepository
import com.nurlandroid.kotapp.repository.NewPostRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val diModule = module {
    single { CustomFragmentFactory() }
    viewModel { MyViewModel(get()) }

    single { NewPostRepository(get(), get()) }
    single { DummyInteractor(get()) }
    single { PostsRepository(get(), get()) }

    single<NetworkApi> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

    single { PostDatabase.getDatabase(androidContext()) }
}