package com.br.contactfetchapp.app

import android.app.Application
import com.br.contactfetchapp.di.databaseModule
import com.br.contactfetchapp.di.networkModule
import com.br.contactfetchapp.di.repositoryModule
import com.br.contactfetchapp.di.useCaseModule
import com.br.contactfetchapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelModule, useCaseModule, repositoryModule, databaseModule, networkModule)
        }
    }
}