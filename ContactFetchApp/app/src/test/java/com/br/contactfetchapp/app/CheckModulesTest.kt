package com.br.contactfetchapp.app

import com.br.contactfetchapp.di.networkModule
import com.br.contactfetchapp.di.repositoryModule
import com.br.contactfetchapp.di.useCaseModule
import com.br.contactfetchapp.di.viewModelModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules() {
        viewModelModule.verify(extraTypes = listOf(com.br.contactfetchapp.domain.usecase.RandomUserUseCase::class))
        repositoryModule.verify()
        useCaseModule.verify()
        networkModule.verify()
    }
}