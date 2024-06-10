package com.amit.kmp.poc.shared.data.sources.remote

import com.amit.kmp.poc.shared.data.api.ApiClient
import com.amit.kmp.poc.shared.data.model.response.FeedDTO
import com.amit.kmp.poc.shared.domain.ApiResponse
import com.amit.kmp.poc.shared.domain.safeRequest
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import org.koin.core.annotation.Single

internal interface FeedRemoteDataSource {
    suspend fun getFeed1(): ApiResponse<List<FeedDTO>, Exception>

    suspend fun getFeed2(): ApiResponse<List<FeedDTO>, Exception>
}

private const val ONE_FOOTBALL_PATH = ".netlify/functions/api/news/onefootball"
private const val ESPN_FOOTBALL_PATH = ".netlify/functions/api/news/espn"

@Single([FeedRemoteDataSource::class])
internal class FeedRemoteDataSourceImpl(
    private val apiClient: ApiClient,
) : FeedRemoteDataSource {
    override suspend fun getFeed1(): ApiResponse<List<FeedDTO>, Exception> {
        return try {
            return apiClient.http.safeRequest {
                get { url { path(ONE_FOOTBALL_PATH) } }
                    .body<List<FeedDTO>>()
            }
        } catch (e: Exception) {
            ApiResponse.Error.NetworkError
        }
    }

    override suspend fun getFeed2(): ApiResponse<List<FeedDTO>, Exception> {
        return try {
            return apiClient.http.safeRequest {
                get { url { path(ESPN_FOOTBALL_PATH) } }
                    .body<List<FeedDTO>>()
            }
        } catch (e: Exception) {
            ApiResponse.Error.NetworkError
        }
    }
}
