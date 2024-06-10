package com.amit.kmp.poc.shared.domain.repository

import com.amit.kmp.poc.shared.domain.ApiResponse
import com.amit.kmp.poc.shared.domain.models.FeedItem
import kotlinx.coroutines.flow.Flow

internal interface FeedRepository {
    fun getFeed(): Flow<List<FeedItem>>

    suspend fun fetchFeed(): ApiResponse<List<FeedItem>, Exception>
}
