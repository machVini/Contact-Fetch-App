package com.br.contactfetchapp.data.repositoryimport com.br.contactfetchapp.data.model.RandomUserimport kotlinx.coroutines.flow.Flowinterface RandomUserRepository {    fun getAllUser(page: Int, pageSize: Int): Flow<List<RandomUser>>}