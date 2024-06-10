package com.amit.kmp.poc.shared.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

private const val BASE_URL = "footballnewsapi.netlify.app"

@Single([ApiClient::class])
internal class ApiClient(
    httpClient: HttpClient,
//    private val preferenceManager: PreferenceManager
) {
    val http =
        httpClient.config {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.BODY
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                        isLenient = true
                    },
                )
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                }
            }
            expectSuccess = true
            addDefaultResponseValidation()

            install("TokenHandler") {
                requestPipeline.intercept(HttpRequestPipeline.Before) {
//                if (context.url.pathSegments.first() != "login") {
//                    preferenceManager.get<String>(Preferences.USER_TOKEN_KEY)?.let {
//                        context.header("Authorization", "Bearer $it")
//                    }
//                }
                }
            }
        }

//    fun storeToken(token: String) {
//        preferenceManager[Preferences.USER_TOKEN_KEY] = token
//    }
}
