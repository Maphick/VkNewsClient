package com.makashovadev.vknewsclient

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.PostComment
import com.makashovadev.vknewsclient.domain.StatisticItem
import com.makashovadev.vknewsclient.ui.theme.HomeScreenState

class MainViewModel : ViewModel() {

    private  val comments = mutableListOf<PostComment>().apply {
        repeat(20){
            add(PostComment(id = it))
        }
    }
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }
    // исходный стейт
    private val initialState =  HomeScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private  var savedState: HomeScreenState? = initialState
    fun showComments(feedPost: FeedPost)
    {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost = feedPost, comments = comments)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun closeComments()
    {
        _screenState.value = savedState
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        //  только если находимся на экране со списком постов
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList() // ?: mutableListOf()
        val oldStatistic = feedPost.statistics
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type)
                    oldItem.copy(count = oldItem.count + 1)
                else oldItem
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistic)
        val newPosts  = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id)
                    newFeedPost
                else
                    it
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList() //?: mutableListOf()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(posts = oldPosts)
    }

}