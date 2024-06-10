package com.amit.kmp.poc.shared.domain.usecase

import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface FeedUseCase {
    fun getFeed(): Flow<List<FeedItem>>
}

@Factory([FeedUseCase::class])
internal class FeedUseCaseImpl(
    private val feedRepository: FeedRepository,
) : FeedUseCase {
    override fun getFeed(): Flow<List<FeedItem>> {
        return feedRepository.getFeed()
    }
}
