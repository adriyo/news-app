package com.adriyo.newsapp.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adriyo.newsapp.R
import com.adriyo.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearchAction: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                modifier = Modifier
                    .semantics {
                        contentDescription = context.getString(R.string.cd_search_input)
                    }
                    .weight(1f)
                    .padding(end = 8.dp),
                textStyle = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchAction() }
                ),
            )
            Icon(
                imageVector = Icons.Outlined.Clear,
                contentDescription = null,
                tint = if (text.isEmpty()) Color.Transparent else Color.Gray,
                modifier = if (text.isEmpty()) Modifier else Modifier.clickable {
                    onValueChange("")
                }
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    NewsAppTheme(darkTheme = true) {
        SearchTextField(
            text = "test",
            onValueChange = {},
            onSearchAction = {}
        )
    }
}