package com.adriyo.newsapp.feature.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adriyo.newsapp.R
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.domain.model.Source
import com.adriyo.newsapp.shared.util.Helper
import com.adriyo.newsapp.shared.component.ArticleItemLarge
import com.adriyo.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleRow(articles: List<Article>, onItemClick: (Article) -> Unit) {
    if (articles.isEmpty()) return
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            stringResource(R.string.top_headlines),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ArticleItemLarge(article = articles[0], onItemClick = onItemClick)
        if (articles.size > 1) {
            Text(
                stringResource(R.string.popular),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(articles.subList(1, articles.size)) { article ->
                    ArticleCard(article = article, onItemClick = onItemClick)
                }
            }
        }
    }
}

@Composable
fun ArticleCard(article: Article, onItemClick: (Article) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .clickable {
                onItemClick(article)
            }
            .width(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.cd_article_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .fillMaxWidth()
                    .height(75.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${article.source.name}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = Helper.getTimeAgo(article.publishedAt),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleRow() {
    NewsAppTheme {
        ArticleRow(
            onItemClick = {},
            articles = (0..6).map {
                Article(
                    source = Source(id = null, name = "Source $it"),
                    author = "Author $it",
                    title = "LAUSD moving forward with plan to ban student cellphone use during school day - KABC-TV $it",
                    description = "Description: $it",
                    url = "https://google.com",
                    urlToImage = "https://via.placeholder.com/150",
                    publishedAt = "2024-06-19T01:05:13Z",
                    content = null
                )
            }
        )
    }
}