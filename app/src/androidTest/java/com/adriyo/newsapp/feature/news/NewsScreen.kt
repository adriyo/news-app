package com.adriyo.newsapp.feature.news

import TestDispatcherProvider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.adriyo.newsapp.core.FakeNewsRepository
import com.adriyo.newsapp.R
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.ui.theme.NewsAppTheme
import org.junit.Rule
import org.junit.Test

class NewsScreenTest {

    @get:Rule
    val screenRule = createComposeRule()

    private val fakeNewsRepository = FakeNewsRepository()
    private val testDispatcher = TestDispatcherProvider()

    @Test
    fun verifyDataAvailable() {
        lateinit var largeImageCd: String
        screenRule.setContent {
            largeImageCd = stringResource(R.string.cd_article_image_large)
            NewsAppTheme {
                NewsScreen(
                    viewModel = NewsViewModel(
                        dispatchers = testDispatcher,
                        repository = fakeNewsRepository
                    ),
                    navigateToSearch = {},
                    navigateToWebView = {}
                )
            }
        }

        // check if categories is displayed
        screenRule.onNodeWithText(Category.BUSINESS.name).assertIsDisplayed()

        // check if top headlines are displayed
        screenRule.onNodeWithText(fakeNewsRepository.topHeadlinesArticles[0].title).assertIsDisplayed()
        screenRule.onNodeWithText(fakeNewsRepository.topHeadlinesArticles[1].title).assertIsDisplayed()

        screenRule.onNodeWithContentDescription(largeImageCd).assertIsDisplayed()
        screenRule.onNodeWithText(fakeNewsRepository.articles[0].title).assertIsDisplayed()
    }

    @Test
    fun verifyErrorScreenShown() {
        fakeNewsRepository.setError()
        lateinit var refreshBtnText: String
        screenRule.setContent {
            refreshBtnText = stringResource(R.string.refresh)
            NewsAppTheme {
                NewsScreen(
                    viewModel = NewsViewModel(
                        dispatchers = testDispatcher,
                        repository = fakeNewsRepository
                    ),
                    navigateToSearch = {},
                    navigateToWebView = {}
                )
            }
        }
        screenRule.onNodeWithText(fakeNewsRepository.articles[0].title).assertIsNotDisplayed()
        screenRule.onNodeWithText(refreshBtnText).isDisplayed()
    }

}