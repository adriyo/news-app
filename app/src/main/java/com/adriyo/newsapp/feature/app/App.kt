package com.adriyo.newsapp.feature.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adriyo.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsApp() {
    NewsAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavGraph()
        }
    }
}