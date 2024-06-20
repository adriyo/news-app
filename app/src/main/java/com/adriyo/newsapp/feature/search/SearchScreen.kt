package com.adriyo.newsapp.feature.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.shared.component.ArticleListContent

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
    navigateToWebView: (Article) -> Unit,
) {
    var queryText by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    SearchTextField(
                        text = queryText,
                        onValueChange = { queryText = it },
                        onSearchAction = {
                            viewModel.searchNews(queryText)
//                            Toast.makeText(context, "COBA", Toast.LENGTH_SHORT)
//                                .show()
                        }
                    )
                },
            )
        }
    ) { paddingValues ->
        val newsPaging = viewModel.searchResults.collectAsLazyPagingItems()
        ArticleListContent(
            modifier = Modifier.padding(paddingValues),
            paging = newsPaging,
            onItemClick = navigateToWebView,
            showFirstItemAsHeader = false
        )
    }
}