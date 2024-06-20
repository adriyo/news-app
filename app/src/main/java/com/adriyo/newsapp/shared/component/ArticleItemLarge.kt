package com.adriyo.newsapp.shared.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adriyo.newsapp.R
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.domain.model.Source
import com.adriyo.newsapp.shared.util.Helper
import com.adriyo.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleItemLarge(article: Article, onItemClick: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                onItemClick(article)
            }
            .padding(horizontal = 16.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.cd_article_image_large),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(160.dp),
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 10.dp)
        )
        if (article.description != null) {
            Text(
                text = article.description,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Light
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = article.author ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = Helper.getTimeAgo(article.publishedAt),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemLargePreview() {
    NewsAppTheme {
        val article = Article(
            source = Source(id = null, name = "Paulette Daniel"),
            author = null,
            title = "senserit",
            description = "lorem ipsum dolor sit amet lorem ipsum dolor sit ametlorem ipsum dolor sit amet",
            url = "https://duckduckgo.com/?q=pretium",
            urlToImage = null,
            publishedAt = "2024-06-19T00:26:22Z",
            content = null
        )
        Surface {
            ArticleItemLarge(article, {})
        }
    }

}