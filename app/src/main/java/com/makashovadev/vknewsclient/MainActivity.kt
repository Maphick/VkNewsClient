package com.makashovadev.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.ui.theme.CommentsScreen
import com.makashovadev.vknewsclient.ui.theme.HomeScreen
import com.makashovadev.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val paddingValues: PaddingValues = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp // для нижней навигации
        )
        setContent {
            VkNewsClientTheme {
                //val navigationState = rememberNavigationState()
                val commentsToPost: MutableState<FeedPost?>  = remember {
                    mutableStateOf(null)
                }
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen (
                        onBackPressed = {
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )

                    }
                }
            }
        }
    }

