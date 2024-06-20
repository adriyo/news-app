package com.adriyo.newsapp.shared.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.adriyo.newsapp.R

@Composable
fun ErrorScreen(modifier : Modifier = Modifier,message: String?, onRefresh: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$message", textAlign = TextAlign.Center)
        Button(onClick = onRefresh) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}
