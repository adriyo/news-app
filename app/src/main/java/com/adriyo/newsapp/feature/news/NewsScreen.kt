package com.adriyo.newsapp.feature.news

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.adriyo.newsapp.R
import com.adriyo.newsapp.shared.component.ArticleListContent
import com.adriyo.newsapp.core.domain.model.Article

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navigateToSearch: () -> Unit,
    navigateToWebView: (Article) -> Unit,
) {
    val newsPaging = viewModel.newsPaging.collectAsLazyPagingItems()
    val currentCategory by viewModel.currentCategory.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = navigateToSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.cd_search)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ArticleListContent(
            modifier = Modifier.padding(paddingValues),
            paging = newsPaging,
            onItemClick = navigateToWebView,
            header = {
                CategoriesHeader(
                    selectedCategory = currentCategory,
                    onSelectCategory = viewModel::onSelectCategory,
                )
            },
            topHeadlines = {
                ArticleRow(
                    articles = uiState.topHeadlineArticles,
                    onItemClick = navigateToWebView
                )
            }
        )
    }
}
