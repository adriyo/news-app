package com.adriyo.newsapp.feature.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.adriyo.newsapp.core.repository.NewsRepository
import com.adriyo.newsapp.core.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _search = MutableStateFlow("")

    fun searchNews(query: String) {
        if (query.isEmpty()) return
        _search.value = query
        println("SEARCH: $query")
    }

    val searchResults: Flow<PagingData<Article>> =
        _search.debounce(300.milliseconds).flatMapLatest {
            repository.searchNewsStream(it)
        }

}
