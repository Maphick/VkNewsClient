package com.makashovadev.vknewsclient.ui.theme

import android.widget.AdapterView.OnItemClickListener
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makashovadev.vknewsclient.NewsFeedViewModel
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.PostComment

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues, onCommentClickListener: (FeedPost) -> Unit
) {
    val viewModel: NewsFeedViewModel = viewModel()
    // стейт экрана: список сетей или список устройств
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {

        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentCliclListener = onCommentClickListener
            )
        }

        is NewsFeedScreenState.Initial -> {}
    }
}


//  отображение экрана со списком постов
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentCliclListener: (FeedPost) -> Unit
) {
    LazyColumn(
        contentPadding = paddingValues, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = { it.id }) { feedPost ->
            val dismissState = rememberDismissState() // разный стейт для каждой компоузебл функции,
            // которую отображает LazyColumn
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                dismissContent = {
                    PostCard(feedPost = feedPost, onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    }, onShareClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    }, onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    }, onCommentClickListener = { statisticItem ->
                        onCommentCliclListener(feedPost)
                    })
                },
                directions = setOf(DismissDirection.EndToStart)
            )
        }
    }
}


