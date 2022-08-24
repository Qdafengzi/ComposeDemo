package com.example.composedemo.data

import androidx.compose.ui.graphics.Color

/**
 * Created by finn on 2022/8/24
 */
data class VoteEntity(
    val percent: Float,
    val color: Color,
    val voteText: String,
)