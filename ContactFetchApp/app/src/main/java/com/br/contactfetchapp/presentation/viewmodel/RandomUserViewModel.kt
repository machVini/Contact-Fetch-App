package com.br.contactfetchapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.contactfetchapp.data.repository.PaginatorImpl
import com.br.contactfetchapp.domain.usecase.RandomUserUseCase
import com.br.contactfetchapp.presentation.viewstate.MainViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomUserViewModel(
    private val randomUserUseCase: RandomUserUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state

    private val paginator = PaginatorImpl(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            randomUserUseCase(nextPage, 10)
        },
        getNextKey = {
            _state.value.page + 1
        },
        onError = {
            _state.value = _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = {users, newKey ->
            _state.value = _state.value.copy(
                users = _state.value.users + users,
                page = newKey,
                endReached = users.isEmpty(),
            )
        }
    )

    init {
        loadNextUsers() // Carrega os usuários ao iniciar a ViewModel
    }

//    fun fetchRandomUsers() {
//        viewModelScope.launch {
//            randomUserUseCase(1, 10).collect { users ->
//                _state.value = _state.value.copy(users = users)
//            }
//        }
//    }

    fun loadNextUsers() {
        viewModelScope.launch {
            paginator.loadNextUsers()
        }
    }
}
