package com.amit.kmp.poc.shared.data.sources.local

import com.amit.kmp.poc.shared.data.database.FeedDao
import feed.Feed
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single([FeedLocalDataSource::class])
internal class FeedLocalDataSourceImpl(
    private val feedDao: FeedDao,
) : FeedLocalDataSource {
    override fun getFeed(): Flow<List<Feed>> {
        return feedDao.getFeed()
    }

    override suspend fun insertFeed(items: List<Feed>) {
        feedDao.insertFeed(items)
    }
}
