package com.amit.kmp.poc.shared.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FeedDTO(
    val title: String,
    val url: String,
    @SerialName("img") val image: String,
)
