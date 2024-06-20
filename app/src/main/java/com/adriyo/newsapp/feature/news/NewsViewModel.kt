package com.adriyo.newsapp.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adriyo.newsapp.core.repository.NewsRepository
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.shared.util.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    repository: NewsRepository,
) : ViewModel() {

    private val _currentCategory = MutableStateFlow(Category.BUSINESS)
    val currentCategory = _currentCategory.asStateFlow()

    val uiState: StateFlow<UiState> = flow {
        emit(UiState(isLoading = true))
        val articles: List<Article> = repository.getTopHeadlines()
        emit(UiState(isLoading = false, topHeadlineArticles = articles))
    }.flowOn(dispatchers.io)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState())

    val newsPaging: Flow<PagingData<Article>> =
        _currentCategory.flatMapLatest {
            repository.getNewsStream(it).cachedIn(viewModelScope)
        }

    fun onSelectCategory(category: Category) {
        if (_currentCategory.value == category) return
        _currentCategory.value = category
    }

}

data class UiState(
    val isLoading: Boolean = false,
    val topHeadlineArticles: List<Article> = emptyList(),
)

