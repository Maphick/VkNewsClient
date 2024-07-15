package com.makashovadev.vknewsclient.ui.theme

import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object Initial:CommentsScreenState ()
    data class Comments(
        val feedPost: FeedPost,
        val comments: MutableList<PostComment>
    ) : CommentsScreenState()
}