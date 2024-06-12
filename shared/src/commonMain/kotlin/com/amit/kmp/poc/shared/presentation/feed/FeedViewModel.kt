package com.amit.kmp.poc.shared.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.amit.kmp.poc.shared.domain.ApiResponse
import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.domain.usecase.FeedUseCase
import com.amit.kmp.poc.shared.domain.usecase.FetchFeedUseCase
import com.amit.kmp.poc.shared.presentation.LoadableDataState
import com.amit.kmp.poc.shared.presentation.feed.model.FeedActions
import com.amit.kmp.poc.shared.presentation.feed.model.FeedEffects
import com.amit.kmp.poc.shared.presentation.feed.model.FeedState
import com.amit.kmp.poc.shared.presentation.feed.model.FeedsContainer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FeedViewModel(
// TODO: Use constructor injection private val feedUseCase: FeedUseCase,
// TODO: Use constructor injection private val fetchFeedUseCase: FetchFeedUseCase,
) : ViewModel(), KoinComponent {
    private val feedUseCase: FeedUseCase by inject()
    private val fetchFeedUseCase: FetchFeedUseCase by inject()

    private val _state = MutableStateFlow(FeedState())
    val state = _state.asStateFlow()

    private val viewStateTrigger = MutableSharedFlow<FeedActions>(replay = 1)

    private val _effects = Channel<FeedEffects>()
    val effect = _effects
        .receiveAsFlow()
        .shareIn(viewModelScope, SharingStarted.Eagerly)

    init {
        viewStateTrigger
            .onStart { emit(FeedActions.Init) }
            .onEach(::processAction)
            .launchIn(viewModelScope)

        viewModelScope.launch {
            feedUseCase.getFeed()
                .collect { feed ->
                    Logger.d { "Fetched cache data: $feed." }
                    if (feed.isEmpty()) {
                        _state.update { it.copy(feed = LoadableDataState.Empty) }
                    } else {
                        _state.update {
                            it.copy(feed = LoadableDataState.Loaded(FeedsContainer(feed)))
                        }
                    }
                }
        }
    }

    private suspend fun processAction(action: FeedActions) {
        Logger.d { "Triggered new action: $action." }
        when (action) {
            FeedActions.Init, FeedActions.Refresh -> getFeed()
            is FeedActions.OnTabItemClick -> _state.update { it.copy(tabIndex = action.index) }
            is FeedActions.OnFeedItemClick -> _effects.send(FeedEffects.OnOpenWebView(action.url))
        }
    }

    private suspend fun getFeed() {
        when (val result = fetchFeedUseCase.getFeed()) {
            is ApiResponse.Error -> handleError(result)
            is ApiResponse.Success -> handleSuccess(result.body)
        }
    }

    private fun handleSuccess(result: List<FeedItem>) {
        Logger.d { "Fetch feed success $result" }
    }

    private fun handleError(result: ApiResponse<List<FeedItem>, Exception>) {
        Logger.e { "News feed fetch failed $result" }
        val feed = _state.value.feed as? LoadableDataState.Loaded ?: return
        if (feed.data.items.isEmpty()) {
            _state.update { it.copy(feed = LoadableDataState.Error) }
        }
    }

    fun dispatch(action: FeedActions) {
        viewStateTrigger.tryEmit(action)
    }
}
