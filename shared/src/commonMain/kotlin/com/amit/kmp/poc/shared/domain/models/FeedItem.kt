package com.amit.kmp.poc.shared.domain.models

import kotlinx.serialization.SerialName

data class FeedItem(
    val title: String,
    val url: String,
    @SerialName("img") val image: String,
    val site: String,
)
