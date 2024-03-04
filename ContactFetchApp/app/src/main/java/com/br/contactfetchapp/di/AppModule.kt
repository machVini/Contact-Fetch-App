package com.br.contactfetchapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.br.contactfetchapp.data.repository.RandomUserRepository
import com.br.contactfetchapp.data.repository.RandomUserRepositoryImpl
import com.br.contactfetchapp.data.source.local.db.RandomUserDatabase
import com.br.contactfetchapp.data.source.remote.api.RandomUserApi
import com.br.contactfetchapp.domain.usecase.RandomUserUseCase
import com.br.contactfetchapp.domain.usecase.RandomUserUseCaseImpl
import com.br.contactfetchapp.presentation.viewmodel.RandomUserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { RandomUserViewModel(get()) }
}
val useCaseModule = module {
    single<RandomUserUseCase> { RandomUserUseCaseImpl(get()) }
}

val repositoryModule = module {
    single<RandomUserRepository> { RandomUserRepositoryImpl(get(), get(), get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), RandomUserDatabase::class.java, "random_user_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<RandomUserDatabase>().getUsersDao() }
}

val networkModule = module {
    single { provideRetrofit() }
    single { provideRandomUserApi(get()) }
    single { provideConnectivityManager(androidContext()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi {
    return retrofit.create(RandomUserApi::class.java)
}

fun provideConnectivityManager(context: Context): ConnectivityManager {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm
}