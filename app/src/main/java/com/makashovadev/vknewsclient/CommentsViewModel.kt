package com.makashovadev.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.PostComment
import com.makashovadev.vknewsclient.ui.theme.CommentsScreenState

class CommentsViewModel(
    feedPost: FeedPost
) : ViewModel() {
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> =_screenState

    init {
        LoadComments(feedPost)
    }

    private fun LoadComments(feedPost: FeedPost)
    {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10)
            {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }

}