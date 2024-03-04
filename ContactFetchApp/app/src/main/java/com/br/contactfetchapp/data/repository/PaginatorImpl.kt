package com.br.contactfetchapp.data.repository

import kotlinx.coroutines.flow.Flow


class PaginatorImpl<Key, RandomUser>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<List<RandomUser>>,
    private inline val getNextKey: suspend (List<RandomUser>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (randomUsers: List<RandomUser>, newKey: Key) -> Unit
): Paginator<Key, RandomUser> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextUsers() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try {
            onRequest(currentKey).collect { users ->
                currentKey = getNextKey(users)
                onSuccess(users, currentKey)
                onLoadUpdated(false)
            }
        } catch (e: Throwable) {
            onError(e)
            onLoadUpdated(false)
        } finally {
            isMakingRequest = false
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}