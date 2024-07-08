package com.makashovadev.vknewsclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.ui.theme.PostCard
import com.makashovadev.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun MainScreen(viewModel: MainViewModel)
{
    val feedPost =  viewModel.feedPost.observeAsState(FeedPost())
        /*remember {
        mutableStateOf(FeedPost())
    }*/

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)
    ) {

        PostCard(
            modifier = Modifier.padding(8.dp),
            feedPost = feedPost.value,
            onStatisticItemClickListener = {
                viewModel.updateCount(it)
            }
        )
    }
}

@Preview
@Composable
fun PreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        //MainScreen()
    }
}

@Preview
@Composable
fun PreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        //MainScreen()
    }
}
