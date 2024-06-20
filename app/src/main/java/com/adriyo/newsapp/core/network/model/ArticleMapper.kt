package com.adriyo.newsapp.core.network.model

import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.domain.model.Source

class ArticleMapper {

    fun map(articles: List<ArticleDto>): List<Article> {
        return articles.map { data ->
            Article(
                source = Source(id = data.source.id, name = data.source.name),
                author = data.author,
                title = data.title,
                description = data.description,
                url = data.url,
                urlToImage = data.urlToImage,
                publishedAt = data.publishedAt,
                content = data.content,
            )
        }
    }

}