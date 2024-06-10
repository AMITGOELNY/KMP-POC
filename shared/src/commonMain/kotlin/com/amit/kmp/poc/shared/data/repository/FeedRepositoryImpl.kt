package com.amit.kmp.poc.shared.data.repository

import com.amit.kmp.poc.shared.data.mappers.mapToFeed
import com.amit.kmp.poc.shared.data.mappers.mapToFeedItem
import com.amit.kmp.poc.shared.data.mappers.toFeedItem
import com.amit.kmp.poc.shared.data.sources.local.FeedLocalDataSource
import com.amit.kmp.poc.shared.data.sources.remote.FeedRemoteDataSource
import com.amit.kmp.poc.shared.domain.ApiResponse
import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single([FeedRepository::class])
internal class FeedRepositoryImpl(
    private val remoteDataSource: FeedRemoteDataSource,
    private val localDataSource: FeedLocalDataSource,
) : FeedRepository {
    override fun getFeed(): Flow<List<FeedItem>> {
        return localDataSource.getFeed().map { it.mapToFeedItem() }
    }

    override suspend fun fetchFeed(): ApiResponse<List<FeedItem>, Exception> {
        return when (val result = remoteDataSource.getFeed1()) {
            is ApiResponse.Success -> {
                val items = result.body.toFeedItem()
                insertFeedRecords(items)
                ApiResponse.Success(items)
            }

            is ApiResponse.Error -> result
        }
    }

    private suspend fun insertFeedRecords(items: List<FeedItem>) {
        val feed = items.mapToFeed()
        localDataSource.insertFeed(feed)
    }
}
