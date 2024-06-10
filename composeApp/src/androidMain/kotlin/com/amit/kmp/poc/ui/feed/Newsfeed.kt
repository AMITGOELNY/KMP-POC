package com.amit.kmp.poc.ui.feed

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.amit.kmp.poc.shared.presentation.LoadableDataState
import com.amit.kmp.poc.shared.presentation.feed.FeedViewModel
import com.amit.kmp.poc.ui.shared.LoadingAnimation
import com.amit.kmp.poc.ui.theme.Dimens
import org.koin.compose.koinInject

@Composable
fun Newsfeed(viewModel: FeedViewModel = koinInject()) {
    val feedState = viewModel.state.collectAsState()

    AnimatedContent(
        targetState = feedState.value.feed,
        transitionSpec = { fadeIn(tween(3000)) togetherWith fadeOut(tween(3000)) },
        label = "Animated Content"
    ) { targetState ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.grid_2_5)
        ) {
            when (targetState) {
                LoadableDataState.Empty -> Text(text = "No Data found")
                LoadableDataState.Error -> TODO()
                is LoadableDataState.Loaded ->
                    NewsItemList(
                        feed = targetState.data,
                        selectedFeed = targetState.data.items,
                        onFeedItemClick = { // TODO
                        }
                    )

                LoadableDataState.Loading ->
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        LoadingAnimation()
                    }
            }
        }
    }
}
