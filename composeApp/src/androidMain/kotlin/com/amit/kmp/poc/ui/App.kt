package com.amit.kmp.poc.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.amit.kmp.poc.ui.feed.Newsfeed
import com.amit.kmp.poc.ui.shared.WebViewScreen
import com.amit.kmp.poc.ui.theme.AppTheme
import kotlinx.serialization.Serializable

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        Surface {
            NavigationGraph(navController)
        }
    }
}

@Composable
private fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NewsFeedView) {
        composable<NewsFeedView> {
            Column(Modifier.fillMaxSize()) {
                Newsfeed { navController.navigate(NewsWebView(it)) }
            }
        }
        composable<NewsWebView> { backStackEntry ->
            val webView: NewsWebView = backStackEntry.toRoute()
            WebViewScreen(webView.url) {
                navController.popBackStack()
            }
        }
    }
}

@Serializable
object NewsFeedView

@Serializable
data class NewsWebView(val url: String)
