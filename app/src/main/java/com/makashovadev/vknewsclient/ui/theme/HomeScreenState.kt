package com.makashovadev.vknewsclient.ui.theme

import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.PostComment

sealed class HomeScreenState {
    object Initial:HomeScreenState()
    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}