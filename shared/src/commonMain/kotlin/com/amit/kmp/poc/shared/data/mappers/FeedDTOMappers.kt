package com.amit.kmp.poc.shared.data.mappers

import com.amit.kmp.poc.shared.data.model.response.FeedDTO
import com.amit.kmp.poc.shared.domain.models.FeedItem

internal fun List<FeedDTO>.toFeedItem() =
    map {
        FeedItem(
            title = it.title,
            image = it.image,
            url = it.url,
            site = "One Football",
        )
    }
