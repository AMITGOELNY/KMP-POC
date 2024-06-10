package com.amit.kmp.poc.shared.domain.usecase

import com.amit.kmp.poc.shared.domain.ApiResponse
import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.domain.repository.FeedRepository
import org.koin.core.annotation.Factory

interface FetchFeedUseCase {
    suspend fun getFeed(): ApiResponse<List<FeedItem>, Exception>
}

@Factory([FetchFeedUseCase::class])
internal class FetchFeedUseCaseImpl(
    private val feedRepository: FeedRepository,
) : FetchFeedUseCase {
    override suspend fun getFeed(): ApiResponse<List<FeedItem>, Exception> {
        return feedRepository.fetchFeed()
    }
}
