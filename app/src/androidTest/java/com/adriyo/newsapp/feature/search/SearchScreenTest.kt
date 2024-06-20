package com.adriyo.newsapp.feature.search

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.adriyo.newsapp.core.FakeNewsRepository
import com.adriyo.newsapp.R
import com.adriyo.newsapp.ui.theme.NewsAppTheme
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val screenRule = createComposeRule()

    private val fakeNewsRepository = FakeNewsRepository()

    @Test
    fun searchScreen_displaysArticles_whenQueryIsEntered() {
        val viewModel = SearchViewModel(fakeNewsRepository)

        lateinit var searchInputCd: String
        screenRule.setContent {
            searchInputCd = stringResource(R.string.cd_search_input)
            NewsAppTheme {
                SearchScreen(
                    viewModel = viewModel,
                    onBack = {},
                    navigateToWebView = {}
                )
            }
        }

        screenRule.onNodeWithContentDescription(searchInputCd).performTextInput("test")
        screenRule.onNodeWithContentDescription(searchInputCd).performImeAction()
        screenRule.waitForIdle()
        val articles = fakeNewsRepository.articles
        screenRule.waitUntil(timeoutMillis = 5000) {
            screenRule.onAllNodesWithText(articles[0].title).fetchSemanticsNodes().isNotEmpty() &&
                    screenRule.onAllNodesWithText(articles[1].title).fetchSemanticsNodes().isNotEmpty()
        }
        screenRule.onNodeWithText(articles[0].title).assertIsDisplayed()
        screenRule.onNodeWithText(articles[1].title).assertIsDisplayed()
    }

}