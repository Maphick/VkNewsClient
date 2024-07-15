package com.makashovadev.vknewsclient.ui.theme

import com.makashovadev.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    object Initial:NewsFeedScreenState ()
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}