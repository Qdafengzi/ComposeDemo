package com.example.composedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.R
import com.example.composedemo.data.VideoEntity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

 class RvVideoAdapter(
    private val lifecycle: Lifecycle
) : RecyclerView.Adapter<RvVideoAdapter.ViewHolder>() {

    var videoList = mutableListOf<VideoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val youTubePlayerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_youtube_list_item, parent, false) as YouTubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        return ViewHolder(youTubePlayerView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.cueVideo(videoList[position].videoId)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ViewHolder(youTubePlayerView: YouTubePlayerView) :
        RecyclerView.ViewHolder(
            youTubePlayerView
        ) {
        private var youTubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@ViewHolder.youTubePlayer = youTubePlayer
                    this@ViewHolder.youTubePlayer?.cueVideo(currentVideoId!!, 0f)
                }
            })
        }

        fun cueVideo(videoId: String?) {
            currentVideoId = videoId
            if (youTubePlayer == null) return
            youTubePlayer?.cueVideo(videoId!!, 0f)
        }
    }
}