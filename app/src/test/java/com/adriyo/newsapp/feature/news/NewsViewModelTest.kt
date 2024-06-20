package com.adriyo.newsapp.feature.news

import TestDispatcherProvider
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.adriyo.newsapp.MainDispatcherRule
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.core.FakeNewsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeNewsRepository = FakeNewsRepository()
    lateinit var SUT: NewsViewModel
    private val testDispatcher = TestDispatcherProvider()

    @Before
    fun setUp() {
        SUT = NewsViewModel(
            dispatchers = testDispatcher,
            repository = fakeNewsRepository
        )
    }

    @Test
    fun `fetch top headlines, return success`() = runTest{
        SUT.uiState.test {
           assertThat(awaitItem().topHeadlineArticles.size).isEqualTo(fakeNewsRepository.topHeadlinesArticles.size)
        }
    }

    @Test
    fun `fetch news with default category, return success`() = runTest {
        val snapshot = SUT.newsPaging.asSnapshot()
        assertThat(snapshot.size).isEqualTo(fakeNewsRepository.articles.size)
    }

    @Test
    fun `fetch news with category changes, return success`() = runTest {
        SUT.onSelectCategory(Category.SPORTS)
        val snapshot = SUT.newsPaging.asSnapshot()
        assertThat(snapshot.size).isEqualTo(fakeNewsRepository.articles.size)
        SUT.currentCategory.test {
            assertThat(awaitItem()).isEqualTo(fakeNewsRepository.currentCategory)
        }
    }

}