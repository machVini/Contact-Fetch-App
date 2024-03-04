package com.br.contactfetchapp.data.repository

interface Paginator<Key, RandomUser> {
    suspend fun loadNextUsers()
    fun reset()
}