package com.adriyo.newsapp.feature.app

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.adriyo.newsapp.R
import com.adriyo.newsapp.feature.news.ArticleScreen
import com.adriyo.newsapp.feature.news.NewsScreen
import com.adriyo.newsapp.feature.search.SearchScreen
import com.adriyo.newsapp.core.domain.model.Article

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Route.News,
        modifier = Modifier
    ) {
        composable<Route.News> {
           NewsScreen (
                navigateToSearch = {
                    navController.navigate(Route.Search) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToWebView = { article: Article ->
                    navController.navigate(Route.WebView(article.url)) {
                        restoreState = true
                    }
                }
            )
        }
        composable<Route.WebView> { backStackEntry ->
            val webView: Route.WebView = backStackEntry.toRoute()
            ArticleScreen(
                url = webView.url,
                onBack = {
                    navController.navigateUp()
                },
                onShareNews = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_news, webView.url))
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            )
        }
        composable<Route.Search> {
            SearchScreen(
                onBack = {
                    navController.navigateUp()
                },
                navigateToWebView = { article: Article ->
                    navController.navigate(Route.WebView(article.url)) {
                        restoreState = true
                    }

                }
            )
        }
    }
}

