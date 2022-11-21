package com.example.composedemo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.databinding.ActivityYoutubeListItemBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class ItemVideoVH(viewBinding: ActivityYoutubeListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        private var youTubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {
            viewBinding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@ItemVideoVH.youTubePlayer = youTubePlayer
                    this@ItemVideoVH.youTubePlayer?.cueVideo(currentVideoId ?: "", 0f)
                }
            })
        }

        fun cueVideo(videoId: String?) {
            currentVideoId = videoId
            if (youTubePlayer == null) return
            youTubePlayer?.cueVideo(videoId!!, 0f)
        }
    }