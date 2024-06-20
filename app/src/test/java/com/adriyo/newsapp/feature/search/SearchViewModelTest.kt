package com.adriyo.newsapp.feature.search

import androidx.paging.testing.asSnapshot
import com.adriyo.newsapp.MainDispatcherRule
import com.adriyo.newsapp.core.FakeNewsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeNewsRepository = FakeNewsRepository()
    lateinit var SUT: SearchViewModel

    @Before
    fun setUp() {
        SUT = SearchViewModel(
            repository = fakeNewsRepository
        )
    }

    @Test
    fun `search news with empty query should return empty data`() = runTest {
        SUT.searchNews("")
        val snapshot = SUT.searchResults.asSnapshot()
        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `search news with query should return data`() = runTest {
        SUT.searchNews("bisnis")
        val snapshot = SUT.searchResults.asSnapshot()
        assertThat(snapshot.size).isEqualTo(fakeNewsRepository.articles.size)
    }

}