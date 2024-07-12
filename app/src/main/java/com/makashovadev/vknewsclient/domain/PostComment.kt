package com.makashovadev.vknewsclient.domain

import androidx.compose.ui.layout.IntrinsicMeasurable
import com.makashovadev.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"
)
