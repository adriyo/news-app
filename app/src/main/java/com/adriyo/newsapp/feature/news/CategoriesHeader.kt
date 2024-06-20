package com.adriyo.newsapp.feature.news

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.ui.theme.NewsAppTheme

@Composable
fun CategoriesHeader(
    modifier: Modifier = Modifier,
    selectedCategory: Category = Category.BUSINESS,
    onSelectCategory: (Category) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
    ) {
        items(Category.entries.size) { index ->
            val category = Category.entries[index]
            FilterChip(
                onClick = {
                    onSelectCategory(category)
                },
                label = {
                    Text(text = category.name, style = MaterialTheme.typography.labelSmall)
                },
                selected = category == selectedCategory,
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CategoriesHeaderPreview() {
    NewsAppTheme {
        CategoriesHeader { }
    }
}