package com.jetpackcompose.domain.pagingsource

import androidx.paging.PagingSource

class PagingSourceFactory<Key : Any, Value : Any>(
    private val pagingSourceFactory: () -> PagingSource<Key, Value>
) : () -> PagingSource<Key, Value> {
    private val pagingSources = mutableListOf<PagingSource<Key, Value>>()

    override fun invoke(): PagingSource<Key, Value> {
        return pagingSourceFactory.invoke().also { pagingSources.add(it) }
    }

    fun invalidate() {
        synchronized(lock = "invalidatePagingSource") {
            for (pagingSource in pagingSources) {
                if (!pagingSource.invalid) {
                    pagingSource.invalidate()
                }
            }

            pagingSources.removeAll {
                it.invalid
            }
        }
    }
}