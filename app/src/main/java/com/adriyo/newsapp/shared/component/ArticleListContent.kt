package com.adriyo.newsapp.shared.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.adriyo.newsapp.R
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.domain.model.Source
import com.adriyo.newsapp.feature.news.ArticleRow
import com.adriyo.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleListContent(
    modifier: Modifier = Modifier,
    paging: LazyPagingItems<Article>,
    onItemClick: (Article) -> Unit,
    showFirstItemAsHeader: Boolean = true,
    header: @Composable (() -> Unit)? = null,
    topHeadlines: @Composable (() -> Unit)? = null,
) {
    val state = paging.loadState
    LazyColumn(modifier = modifier) {
        if (header != null) {
            stickyHeader { header() }
        }
        if (topHeadlines != null) {
            item { topHeadlines() }
        }
        if (state.refresh is LoadState.Loading) {
            item {
                Box(
                    modifier = modifier
                        .fillParentMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            return@LazyColumn
        }

        if (state.refresh is LoadState.Error) {
            item {
                ErrorScreen(
                    modifier = Modifier.fillParentMaxSize(),
                    message = "${(state.refresh as LoadState.Error).error.message}",
                    onRefresh = { paging.refresh() },
                )
            }
            return@LazyColumn
        }

        items(paging.itemCount) { index ->
            val article = paging[index] ?: return@items
//            if (index == 0) {
//                if (showFirstItemAsHeader) {
//                    ArticleItemLarge(article = article, onItemClick = onItemClick)
//                } else {
//                    ArticleItem(article = article, onItemClick = onItemClick)
//                }
//            } else {
//                ArticleItem(article = article, onItemClick = onItemClick)
//            }
            ArticleItem(article = article, onItemClick = onItemClick)
            HorizontalDivider()
        }

        if (state.append is LoadState.Loading) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.msg_loading))
                }
            }
        }

        if (state.append is LoadState.Error) {
            item {
                ErrorScreen(
                    message = "${(state.append as LoadState.Error).error.message} ",
                    onRefresh = { paging.retry() },
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleListContentSuccessPreview() {
    NewsAppTheme {
        val articles = (0..4).map { index ->
            Article(
                source = Source(id = null, name = "Kenya Castillo"),
                author = null,
                title = "News Title $index",
                description = null,
                url = "http://www.bing.com/search?q=deserunt",
                urlToImage = null,
                publishedAt = "2024-06-19T01:10:23Z",
                content = null
            )
        }
        val paging = flowOf(
            PagingData.from(
                articles,
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        ).collectAsLazyPagingItems()
        Surface {
            ArticleListContent(
                paging = paging,
                onItemClick = {},
                showFirstItemAsHeader = false,
                topHeadlines = {
                    ArticleRow(articles = articles, onItemClick = {})
                }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleListContentErrorPreview() {
    NewsAppTheme {
        val paging = flowOf(
            PagingData.empty<Article>(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(error = Throwable("Error Refresh")),
                    append = LoadState.Error(error = Throwable("Error Append")),
                    prepend = LoadState.Error(error = Throwable("Error Prepend")),
                ),
            )
        ).collectAsLazyPagingItems()
        Surface {
            ArticleListContent(
                paging = paging,
                onItemClick = {},
                showFirstItemAsHeader = false,
            )
        }
    }
}
