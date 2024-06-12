package com.amit.kmp.poc.shared.presentation.feed.model

import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.presentation.LoadableDataState

data class FeedState(
    val tabIndex: Int = 0,
    val feed: LoadableDataState<FeedsContainer> = LoadableDataState.Loading,
)

sealed interface FeedActions {
    data object Init : FeedActions

    data object Refresh : FeedActions

    data class OnTabItemClick(val index: Int) : FeedActions

    data class OnFeedItemClick(val url: String) : FeedActions
}

sealed interface FeedEffects {
    data class OnOpenWebView(val url: String) : FeedEffects
}

data class FeedsContainer(
    val items: List<FeedItem>,
)
