package com.makashovadev.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.makashovadev.vknewsclient.ui.theme.HomeScreen
import com.makashovadev.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val paddingValues: PaddingValues = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
        )
        setContent {
            VkNewsClientTheme {
                HomeScreen(
                    viewModel,
                    paddingValues
                )
            }
        }
    }

}

