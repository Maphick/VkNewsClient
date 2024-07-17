package com.makashovadev.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.ui.theme.CommentsScreen
import com.makashovadev.vknewsclient.ui.theme.HomeScreen
import com.makashovadev.vknewsclient.ui.theme.MainScreen
import com.makashovadev.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                MainScreen()
            }
        }
    }
}