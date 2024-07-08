package com.makashovadev.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makashovadev.vknewsclient.domain.FeedPost
import com.makashovadev.vknewsclient.domain.StatisticItem

class MainViewModel: ViewModel() {
    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem) {
        val oldStatistic = feedPost.value?.statistics ?: throw IllegalStateException("No such statistic type")
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll{ oldItem ->
                if (oldItem.type == item.type)
                    oldItem.copy(count = oldItem.count+1)
                else oldItem
            }
        }
        _feedPost.value = feedPost.value?.copy(statistics = newStatistic)
    }

}