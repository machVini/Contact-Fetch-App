package com.br.contactfetchapp.presentation.viewstate

import com.br.contactfetchapp.data.model.RandomUser

data class MainViewState(
    val isLoading: Boolean = false,
    val users: List<RandomUser> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
