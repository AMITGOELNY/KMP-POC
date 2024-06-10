package com.amit.kmp.poc.shared.data.sources.local

import feed.Feed
import kotlinx.coroutines.flow.Flow

internal interface FeedLocalDataSource {
    fun getFeed(): Flow<List<Feed>>

    suspend fun insertFeed(items: List<Feed>)
}
