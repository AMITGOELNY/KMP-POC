package com.amit.kmp.poc.ui.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.amit.kmp.poc.shared.domain.models.FeedItem
import com.amit.kmp.poc.shared.presentation.feed.model.FeedsContainer
import com.amit.kmp.poc.ui.theme.Dimens
import kmp_rccl_poc.composeapp.generated.resources.Res
import kmp_rccl_poc.composeapp.generated.resources.ic_placeholder
import org.jetbrains.compose.resources.painterResource

@Composable
fun NewsItemList(
    feed: FeedsContainer,
//    tabIndex: Int,
    selectedFeed: List<FeedItem>,
    onFeedItemClick: (String) -> Unit,
//    onTabItemClick: (Int) -> Unit,
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimens.grid_2),
        modifier = Modifier.padding(horizontal = 20.dp),
        contentPadding = PaddingValues(bottom = Dimens.grid_2)
    ) {
        item {
            Text(
                text = "Trending News",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 20.8.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )
        }

        items(selectedFeed, key = { it.url }) {
            NewsItem(it, onFeedItemClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.NewsItem(feedItem: FeedItem, onFeedItemClick: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF2A2B39), RoundedCornerShape(size = 8.dp))
            .background(Color(0xFF23252F), RoundedCornerShape(size = 8.dp))
            .clickable { onFeedItemClick(feedItem.url) }
            .animateItemPlacement(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.grid_1_5)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(feedItem.image)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            placeholder = painterResource(Res.drawable.ic_placeholder),
            error = painterResource(Res.drawable.ic_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = Dimens.grid_1,
                        bottomStart = Dimens.grid_1
                    )
                )
        )
        Column(
            modifier = Modifier
                .height(100.dp)
                .padding(end = Dimens.grid_1_5)
                .padding(vertical = Dimens.grid_1_5),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                feedItem.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    feedItem.site,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 11.sp,
                        lineHeight = 16.5.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFFFF6861)
                )
            }
        }
    }
}