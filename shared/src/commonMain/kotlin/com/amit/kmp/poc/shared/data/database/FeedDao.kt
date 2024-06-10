package com.amit.kmp.poc.shared.data.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import feed.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single([FeedDao::class])
class FeedDao(private val databaseProvider: DatabaseProvider) {
    private val dbRef = databaseProvider.database

    suspend fun insertFeed(feed: List<Feed>) {
        databaseProvider.database.transaction {
            feed.forEach(dbRef.feedQueries::insertFeed)
        }
    }

    fun getFeed(): Flow<List<Feed>> {
        return dbRef.feedQueries.getFeed().asFlow().mapToList(Dispatchers.IO)
    }
}
