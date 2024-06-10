package com.amit.kmp.poc.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amit.kmp.poc.ui.feed.Newsfeed
import com.amit.kmp.poc.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Surface {
            Column(Modifier.fillMaxSize()) {
                Newsfeed()
            }
        }
    }
}
