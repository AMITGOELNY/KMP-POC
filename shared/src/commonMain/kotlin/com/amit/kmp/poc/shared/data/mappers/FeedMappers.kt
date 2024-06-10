package com.amit.kmp.poc.shared.data.mappers

import com.amit.kmp.poc.shared.domain.models.FeedItem
import feed.Feed

internal fun List<Feed>.mapToFeedItem(): List<FeedItem> =
    map {
        FeedItem(
            title = it.title,
            image = it.image,
            url = it.url,
            site = it.site,
        )
    }

internal fun List<FeedItem>.mapToFeed(): List<Feed> =
    map {
        Feed(
            title = it.title,
            image = it.image,
            url = it.url,
            site = it.site,
        )
    }
